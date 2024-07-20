package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;

import java.util.List;

public interface CartRepository {
    int save(Cart cart);

    List<Cart> findAllProductByCart(int userId);
}
