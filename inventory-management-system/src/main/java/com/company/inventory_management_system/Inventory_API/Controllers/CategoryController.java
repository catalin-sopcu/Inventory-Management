package com.company.inventory_management_system.Inventory_API.Controllers;

import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryRequestDTO;
import com.company.inventory_management_system.Inventory_BLL.DTOs.CategoryResponseDTO;
import com.company.inventory_management_system.Inventory_BLL.Interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Optional<CategoryResponseDTO> category = categoryService.getCategoryById(id);
        return category.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        String response = categoryService.createCategory(categoryRequestDTO);
        if (response.startsWith("ERROR:")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        String response = categoryService.updateCategory(id, categoryRequestDTO);
        if (response.startsWith("ERROR:")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
