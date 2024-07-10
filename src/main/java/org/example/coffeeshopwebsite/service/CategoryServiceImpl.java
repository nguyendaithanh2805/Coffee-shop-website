package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        try {
            categoryRepository.save(category);
        }catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}
