package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Articles;

import java.util.List;

public interface ArtilcesService {
    List<Articles> getAllArticles();

    void saveArticle(Articles articles);
}
