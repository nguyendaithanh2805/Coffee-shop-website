package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Articles;
import org.example.coffeeshopwebsite.service.ArtilcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ArticlesController {

    @Autowired
    private ArtilcesService artilcesService;

    @GetMapping("/unapproved-articles")
    public String unapprovedArticles(Model model) {
        List<Articles> articlesList = artilcesService.getAllArticles();
        model.addAttribute("articles", articlesList);
        return "admin/unapproved-articles";
    }

    @GetMapping("/approved-articles")
    public String approvedArticles(Model model) {
        List<Articles> articlesList = artilcesService.getAllArticles();
        model.addAttribute("articles", articlesList);
        return "admin/approved-articles";
    }

    @GetMapping("/add-article")
    public String showArticleForm(Model model) {
        model.addAttribute("article", new Articles());
        return "admin/article-form";
    }

    @PostMapping("/save-article")
    public String saveArticle(@ModelAttribute("article") Articles articles, @RequestParam("imageFile") MultipartFile file, @RequestParam(name = "status", required = false) Boolean status) {
        String fileName = file.getOriginalFilename();
        String uploadDir = "D:\\workspace\\java\\Coffee-shop-website\\src\\main\\resources\\static\\user\\images";
        Path uploadPath = Paths.get(uploadDir);
        try {
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName);

            if (!(Files.exists(filePath)))
                Files.copy(file.getInputStream(), filePath);

            if (articles.getArticleId() == null)
                articles.setStatus(false);
            else
                articles.setStatus(status);

            articles.setArticleImage(fileName);
            artilcesService.saveArticle(articles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/unapproved-articles";
    }

    @GetMapping("/edit-article")
    public String showFormForUpdate(@RequestParam Long id, Model model) {
        Articles articles = artilcesService.getArticlesById(id);
        List<Boolean> statusOptions = Arrays.asList(false, true);
        model.addAttribute("statusOptions", statusOptions);
        model.addAttribute("article", articles);
        return "admin/update-article-form";
    }

    @GetMapping("/delete-article")
    public String deleteArticle(@RequestParam Long id) {
        artilcesService.deleteArticleById(id);
        return "redirect:/admin/approved-articles";
    }

    @PostMapping("update-status")
    public String updateArticleStatus(@RequestParam Long id, @RequestParam Boolean status) {
        Articles article = artilcesService.getArticlesById(id);
        article.setStatus(status);
        artilcesService.saveArticle(article);
        return "redirect:/admin/unapproved-articles";
    }
}
