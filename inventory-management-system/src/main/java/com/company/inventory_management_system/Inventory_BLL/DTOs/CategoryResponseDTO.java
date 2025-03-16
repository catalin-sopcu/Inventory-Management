package com.company.inventory_management_system.Inventory_BLL.DTOs;

import lombok.Data;
import java.util.*;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<ProductResponseDTO> products;
}
