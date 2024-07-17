package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;

public interface CartService {
    void addProductToCart(Cart cart, Product product, int productId, int quantity);
}
