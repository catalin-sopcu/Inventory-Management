package com.company.inventory_management_system.Inventory_BLL.Services;

import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryRequestDTO;
import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryResponseDTO;
import com.company.inventory_management_system.Inventory_BLL.Mappers.CategoryMapper;
import com.company.inventory_management_system.Inventory_BLL.Interfaces.ICategoryService;
import com.company.inventory_management_system.Inventory_DATA.Entities.Category;
import com.company.inventory_management_system.Inventory_DATA.Interfaces.ICategoryRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final Validator validator;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, CategoryMapper categoryMapper, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.validator = validator;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                         .map(categoryMapper::mapToDTO)
                         .collect(Collectors.toList());
    }

    public Optional<CategoryResponseDTO> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::mapToDTO); 
    }

    public String createCategory(CategoryRequestDTO categoryRequestDTO) {
        Set<ConstraintViolation<CategoryRequestDTO>> violations = validator.validate(categoryRequestDTO);
        if (!violations.isEmpty()) {
            return "ERROR: " + violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }

        Category category = categoryMapper.mapToEntity(categoryRequestDTO);
        categoryRepository.save(category);
        return "Category created successfully.";
    }

    public String updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Set<ConstraintViolation<CategoryRequestDTO>> violations = validator.validate(categoryRequestDTO);
        if (!violations.isEmpty()) {
            return "ERROR: " + violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }
    
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryRequestDTO.getName());
            categoryRepository.save(category);
            return "Category updated successfully.";
        } else {
            return "ERROR: Category with ID " + id + " not found.";
        }
    }    

    public boolean deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Category not found");
    }
}
