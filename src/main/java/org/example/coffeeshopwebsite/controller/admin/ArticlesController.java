package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Articles;
import org.example.coffeeshopwebsite.service.ArtilcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ArticlesController {

    @Autowired
    private ArtilcesService artilcesService;

    @GetMapping("/articles-list")
    public String articlesList(Model model) {
        List<Articles> articlesList = artilcesService.getAllArticles();
        model.addAttribute("articles", articlesList);
        return "admin/articles";
    }

    @GetMapping("/add-article")
    public String showArticleForm(Model model) {
        model.addAttribute("article", new Articles());
        return "admin/article-form";
    }

    @PostMapping("/save-article")
    public String saveArticle(@ModelAttribute("article") Articles articles, @RequestParam("imageFile") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String uploadDir = "D:\\workspace\\java\\Coffee-shop-website\\src\\main\\resources\\static\\user\\images";
        Path uploadPath = Paths.get(uploadDir);
        try {
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName);

            if (!(Files.exists(filePath)))
                Files.copy(file.getInputStream(), filePath);
            articles.setArticleImage(fileName);
            artilcesService.saveArticle(articles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/articles-list";
    }
}
