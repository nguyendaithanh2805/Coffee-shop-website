package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    void saveCategory(Category category);

    Category findCategoryById(int id);

    void updateCategory(Category category);

    void deleteCategoryById(int id);
}
