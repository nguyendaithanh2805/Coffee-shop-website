package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Products;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface ProductsService {
    List<Products> getAllProduct();

    void saveProduct(Products products);

    Products getProductById(Long id);
}
