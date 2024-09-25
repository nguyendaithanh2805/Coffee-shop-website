package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {
    List<OrderDetail> findAll();
    int save(OrderDetail orderDetail);
    int deleteById(int id);

    List<OrderDetail> findByOrderId(int orderId);
}
