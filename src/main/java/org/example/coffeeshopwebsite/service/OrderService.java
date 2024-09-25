package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);

    List<Order> getAllOrder();

    Order getOrderById(int id);

    void updateOrder(Order order);

    void deleteOrderById(int id);
}
