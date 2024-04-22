package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Categories;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface CategoryService {

    void saveCategory(Categories categories);

    List<Categories> getAllCategory();

    Categories getCategoryById(Long catId);
}
