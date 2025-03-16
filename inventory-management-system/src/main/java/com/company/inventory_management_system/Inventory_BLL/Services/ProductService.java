package com.company.inventory_management_system.Inventory_BLL.Services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.inventory_management_system.Inventory_BLL.DTOs.*;
import com.company.inventory_management_system.Inventory_BLL.Interfaces.IProductService;
import com.company.inventory_management_system.Inventory_BLL.Mappers.*;
import com.company.inventory_management_system.Inventory_DATA.Entities.Product;
import com.company.inventory_management_system.Inventory_DATA.Interfaces.IProductRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final Validator validator;
    private final ProductMapper productMapper;

    public ProductService(IProductRepository productRepository, Validator validator, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.validator = validator;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse) 
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse);
    }

    public Page<ProductResponseDTO> getPaginatedProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }

    public String addProduct(ProductRequestDTO product) {
        Set<ConstraintViolation<ProductRequestDTO>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            return "ERROR: " + violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }
        var mappedProduct = productMapper.toEntity(product);
        productRepository.save(mappedProduct);
        return "Product created successfully.";
    }

    public String updateProduct(Long id, ProductRequestDTO updatedProduct) {
        Set<ConstraintViolation<ProductRequestDTO>> violations = validator.validate(updatedProduct);
        if (!violations.isEmpty()) {
            return "ERROR: " + violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }
    
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            productRepository.save(product);
            return "Product updated successfully.";
        }
        return "ERROR: Product with ID " + id + " not found.";
    }
    

    public void increaseStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);
    }
    
    public boolean decreaseStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    
        if (product.getQuantity() < quantity) {
            return false;
        }
    
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        return true;
    }
    
    public List<ProductResponseDTO> getLowStockProducts() {
        List<Product> lowStockProducts = productRepository.findByQuantityLessThanEqual(5);
        return lowStockProducts.stream()
                .map(productMapper :: toResponse)
                .collect(Collectors.toList());
    }
    
    public boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
