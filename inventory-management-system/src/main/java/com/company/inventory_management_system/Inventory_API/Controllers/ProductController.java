package com.company.inventory_management_system.Inventory_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory_management_system.Inventory_BLL.DTOs.ProductRequestDTO;
import com.company.inventory_management_system.Inventory_BLL.DTOs.ProductResponseDTO;
import com.company.inventory_management_system.Inventory_BLL.Interfaces.IProductService;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // @GetMapping
    // public List<ProductResponseDTO> getAllProducts() {
    //     return productService.getAllProducts();
    // }

    @GetMapping
    public Page<ProductResponseDTO> getProducts(Pageable pageable) {
        return productService.getPaginatedProducts(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDTO product) {
        String result = productService.addProduct(product);
        if (result.contains("ERROR")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO updatedProduct) {
        String response = productService.updateProduct(id, updatedProduct);
        if (response.startsWith("ERROR:")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/low-stock")
    public List<ProductResponseDTO> getLowStockProducts() {
        return productService.getLowStockProducts();
    }

    @PutMapping("/{id}/increase-stock")
    public ResponseEntity<String> increaseStock(@PathVariable Long id, @RequestParam int quantity) {
        productService.increaseStock(id, quantity);
        return ResponseEntity.ok("Stock increased successfully.");
    }

    @PutMapping("/{id}/decrease-stock")
    public ResponseEntity<String> decreaseStock(@PathVariable Long id, @RequestParam int quantity) {
        boolean success = productService.decreaseStock(id, quantity);
        if (success) {
            return ResponseEntity.ok("Stock decreased successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough stock available.");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}
