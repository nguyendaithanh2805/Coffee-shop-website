package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
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
    public void saveCategory(Category category) {
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

    @Override
    public void updateCategory(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Can not Cannot delete or update Category because product existing");
        }
    }
}
