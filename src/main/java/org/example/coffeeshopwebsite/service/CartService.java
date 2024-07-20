package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;

import java.util.List;

public interface CartService {
    void addProductToCart(Cart cart, Product product, int productId, int quantity);

    List<Cart> getAllProductByCart();

    void deleteProductInCartById(int id);
}
