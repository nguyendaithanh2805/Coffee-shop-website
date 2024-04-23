package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Articles;
import org.example.coffeeshopwebsite.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArtilcesService{
    private final ArticlesRepository articlesRepository;

    @Autowired
    public ArticlesServiceImpl(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @Override
    public List<Articles> getAllArticles() {
        return articlesRepository.findAll();
    }

    @Override
    public void saveArticle(Articles articles) {
        articles.setArticleName(articles.getArticleName());
        articles.setDescription(articles.getDescription());
        articlesRepository.save(articles);
    }

    @Override
    public Articles getArticlesById(Long id) {
        return articlesRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found articles by Id :: " + id));
    }

    @Override
    public void deleteArticleById(Long id) {
        articlesRepository.deleteById(id);
    }
}
