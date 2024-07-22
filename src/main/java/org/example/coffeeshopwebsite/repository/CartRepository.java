package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;

import java.util.List;

public interface CartRepository {
    int save(Cart cart);
    int deleteById(int id);
    List<Cart> findAllProductByCart(int userId);

    Cart findByProductId(int productId);

    void update(Cart existingCart);
}
