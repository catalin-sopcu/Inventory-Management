package com.company.inventory_management_system.Inventory_BLL.DTOs;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.company.inventory_management_system.Inventory_BLL.Constants.CategoryValidationConstants;

@Data
public class CategoryRequestDTO {

    @NotBlank(message = CategoryValidationConstants.CATEGORY_NAME_REQUIRED) 
    @Size(min = 3, max = 50, message = CategoryValidationConstants.CATEGORY_NAME_SIZE) 
    private String name;
}
