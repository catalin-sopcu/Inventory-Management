package com.company.inventory_management_system.Inventory_BLL.Interfaces;

import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryRequestDTO;
import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    
    String createCategory(CategoryRequestDTO categoryRequestDTO);
    
    List<CategoryResponseDTO> getAllCategories();
    
    Optional<CategoryResponseDTO> getCategoryById(Long id);
    
    String updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);
    
    boolean deleteCategory(Long id);
}
