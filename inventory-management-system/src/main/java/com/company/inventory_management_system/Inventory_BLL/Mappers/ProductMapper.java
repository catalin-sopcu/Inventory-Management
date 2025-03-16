package com.company.inventory_management_system.Inventory_BLL.Mappers;

import org.springframework.stereotype.Component;
import com.company.inventory_management_system.Inventory_BLL.DTOs.*;
import com.company.inventory_management_system.Inventory_DATA.Entities.Product;
import com.company.inventory_management_system.Inventory_DATA.Entities.Category;

@Component
public class ProductMapper {
    public ProductResponseDTO toResponse(Product product) {
        if (product == null) {
            return null;
        }
        
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        
        if (product.getCategory() != null) {
            response.setCategoryId(product.getCategory().getId());
        }
        
        return response;
    }

    public Product toEntity(ProductRequestDTO request) {
        if (request == null) {
            return null;
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Category category = new Category();
        category.setId(request.getCategoryId());
        product.setCategory(category);

        return product;
    }
}
