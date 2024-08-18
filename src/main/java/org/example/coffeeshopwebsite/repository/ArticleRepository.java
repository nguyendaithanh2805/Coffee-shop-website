package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Article;

import java.util.List;

public interface ArticleRepository {
    int save(Article article);
    int deleteById(int id);
    void update(Article article);

    List<Article> findAll();

    Article findById(int id);
}
