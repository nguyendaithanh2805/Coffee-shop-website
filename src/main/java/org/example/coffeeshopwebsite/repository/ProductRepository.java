package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    int save(Product product);
    int update(Product product);
    int deleteById(int id);
}
