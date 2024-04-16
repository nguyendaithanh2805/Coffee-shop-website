package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_Categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;
    private String categoryName;

    @OneToMany(mappedBy = "categories")
    private List<Articles> articles;

    @OneToMany(mappedBy = "categories")
    private List<Products> products;

    public Categories() {}

    public Categories(Long catId, String categoryName, List<Articles> articles, List<Products> products) {
        this.catId = catId;
        this.categoryName = categoryName;
        this.articles = articles;
        this.products = products;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}

