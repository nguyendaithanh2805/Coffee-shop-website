package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    @Override
    public void saveArticle(Article article) {
        User user = userService.getCurrentUser();
        article.setUserId(user.getUserId());
        articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public void deleteArticleById(int id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateArticle(Article article, User user) {
        article.setUserId(user.getUserId());
        articleRepository.update(article);
    }
}
