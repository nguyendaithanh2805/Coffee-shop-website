package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Categories;
import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void saveCategory(Categories categories) {
        Categories existingCategory = categoryRepository.findCategoriesByName(categories.getCategoryName());
        if (existingCategory == null)
            categories.setCategoryName(categories.getCategoryName());
        else
            return;
        categoryRepository.save(categories);
    }

    @Override
    public List<Categories> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategoryById(Long catId) {
        return categoryRepository.findById(catId).orElseThrow(() -> new RuntimeException("Not found Category Id :: " + catId));
    }
}
