package org.example.coffeeshopwebsite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tbl_accounts")
public class Accounts {

    @Id
    private String username;
    private String password;
    private String fullName;

    @OneToMany(mappedBy = "accounts")
    private List<Orders> orders;

    @OneToMany(mappedBy = "accounts")
    private List<Articles> articles;

    @OneToMany(mappedBy = "accounts")
    private List<Products> products;

    public Accounts() {}

    public Accounts(String username, String password, String fullName, List<Orders> orders, List<Articles> articles, List<Products> products) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.orders = orders;
        this.articles = articles;
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
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

