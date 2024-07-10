package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    void saveProduct(Product product);

    Product findProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(int id);
}
