package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();

    int save(Order order);

    Order findById(int id);

    void update(Order order);

    void deleteById(int id);
}
