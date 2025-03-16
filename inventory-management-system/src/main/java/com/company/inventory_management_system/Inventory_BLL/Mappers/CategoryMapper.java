package com.company.inventory_management_system.Inventory_BLL.Mappers;

import com.company.inventory_management_system.Inventory_DATA.Entities.Category;
import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryResponseDTO;
import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDTO mapToDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category mapToEntity(CategoryResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    public Category mapToEntity(CategoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
