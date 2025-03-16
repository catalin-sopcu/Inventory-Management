package com.company.inventory_management_system.Inventory_BLL.DTOs;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Long categoryId; 
}
