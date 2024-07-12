package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    void saveProduct(Product product, User user);

    Product findProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(int id);
}
