package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(int id);
    int save(Category category);
    int update(Category category);
    int deleteById(int id);
}
