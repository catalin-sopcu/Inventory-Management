package com.company.inventory_management_system.Inventory_BLL.Interfaces;

import com.company.inventory_management_system.Inventory_BLL.DTOs.ProductRequestDTO;
import com.company.inventory_management_system.Inventory_BLL.DTOs.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<ProductResponseDTO> getAllProducts();
    Optional<ProductResponseDTO> getProductById(Long id);
    Page<ProductResponseDTO> getPaginatedProducts(Pageable pageable);
    List<ProductResponseDTO> getLowStockProducts();
    String addProduct(ProductRequestDTO product);
    void increaseStock(Long productId, int quantity);
    boolean decreaseStock(Long productId, int quantity);
    String updateProduct(Long id, ProductRequestDTO updatedProduct);
    boolean deleteProduct(Long id);
}
