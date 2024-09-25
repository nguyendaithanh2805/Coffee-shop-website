package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    private final ArticleService articleService;

    @Autowired
    public BlogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/blog")
    public String home(Model model) {
        model.addAttribute("blogs", articleService.getAllArticles());
        return "user/blog";
    }
}
