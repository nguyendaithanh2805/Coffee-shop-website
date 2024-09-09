package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // READ
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/categories";
    }

    // CREATE
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/add-category";
    }

    @PostMapping("/add")
    public String addCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "admin/edit-category";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable int id, Category category) {
        category.setCategoryId(id);
        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }
    // DELETE
    @GetMapping("/confirm-delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable int id, Model model) {
        Category category = categoryService.findCategoryById(id);
        if (category == null) return "redirect:/admin/categories";

        model.addAttribute("entityName", "category");
        model.addAttribute("entityDisplayName", category.getName());
        model.addAttribute("entityId", category.getCategoryId());
        model.addAttribute("deleteUrl", "/admin/categories/delete");
        return "admin/delete";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }
}
