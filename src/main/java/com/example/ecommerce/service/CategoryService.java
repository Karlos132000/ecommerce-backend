package com.example.ecommerce.service;

import com.example.ecommerce.dto.CategoryDTO;
import com.example.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAllCategories();
    CategoryDTO getCategoryById(Long id);
    Category saveCategory(Category categoryDTO);
    void deleteCategory(Long id);
}
