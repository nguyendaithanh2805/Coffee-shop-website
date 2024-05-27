package org.example.coffeeshopwebsite.controller.user;

import org.example.coffeeshopwebsite.model.Articles;
import org.example.coffeeshopwebsite.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public String getBlogs(Model model) {
        List<Articles> articlesList = blogService.getAllBlog();
        model.addAttribute("blog",articlesList);
        return "user/blog";
    }
}
