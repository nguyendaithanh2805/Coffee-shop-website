package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Categories;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        List<Categories> categoriesList = categoryService.getAllCategory();
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("category", new Categories());
        return "admin/category-form";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("category") Categories categories) {
        categoryService.saveCategory(categories);
        return "redirect:/admin/add-category";
    }
}
