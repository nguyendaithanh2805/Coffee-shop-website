package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.ArticleService;
import org.example.coffeeshopwebsite.service.FileUploadService;
import org.example.coffeeshopwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final FileUploadService fileUploadService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, FileUploadService fileUploadService, UserService userService) {
        this.articleService = articleService;
        this.fileUploadService = fileUploadService;
        this.userService = userService;
    }

    // CREATE
    @GetMapping("/add")
    public String showArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "admin/add-article";
    }

    @PostMapping("/add")
    public String saveArticle(@ModelAttribute("article") Article article,
                              @RequestParam("imageFile") MultipartFile file,
                              @RequestParam(name = "status", required = false) Boolean status) {
        fileUploadService.handleImageUpload(article, file);
        if (article.getArticleId() == 0)
            article.setStatus(false);
        else
            article.setStatus(status);
        articleService.saveArticle(article);
        return "redirect:/admin/articles";
    }

    // READ
    @GetMapping
    public String approvedArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "admin/articles";
    }

    // UPDATE
    @GetMapping("/edit")
    public String showFormForUpdate(@RequestParam int id, Model model) {
        Article article = articleService.getArticleById(id);
        List<Boolean> statusOptions = Arrays.asList(false, true);
        model.addAttribute("statusOptions", statusOptions);
        model.addAttribute("article", article);
        return "admin/edit-article";
    }

    @PostMapping("/edit")
    public String updateArticle(@ModelAttribute("article") Article article,
                              @RequestParam("imageFile") MultipartFile file,
                              @RequestParam(name = "status", required = false) Boolean status) {
        fileUploadService.handleImageUpload(article, file);
        if (article.getArticleId() == 0)
            article.setStatus(false);
        else
            article.setStatus(status);
        User user = userService.getCurrentUser();
        articleService.updateArticle(article, user);
        return "redirect:/admin/articles";
    }

    @PostMapping("/edit-status")
    public String updateArticleStatus(@RequestParam int id, @RequestParam(name = "status", required = false) Boolean status) {
        Article article = articleService.getArticleById(id);
        article.setStatus(status);
        articleService.updateArticle(article, userService.getCurrentUser());
        return "redirect:/admin/articles";
    }
    // DELETE
    @GetMapping("/confirm-delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable int id, Model model) {
        Article article = articleService.getArticleById(id);
        if (article == null) return "redirect:/admin/articles";
        model.addAttribute("entityName", "article");
        model.addAttribute("entityDisplayName", article.getName());
        model.addAttribute("entityId", article.getArticleId());
        model.addAttribute("deleteUrl", "/admin/articles/delete");
        return "admin/delete";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        articleService.deleteArticleById(id);
        return "redirect:/admin/articles";
    }
}
