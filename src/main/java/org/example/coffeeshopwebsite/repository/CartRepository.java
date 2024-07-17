package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;

public interface CartRepository {
    int save(Cart cart);
}
