package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Order;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public void saveOrder(Order order) {
        User user = userService.getCurrentUser();
        Date orderDate = new Date();
        Date deliveryDate = calculateDeliveryDate(orderDate);
        order.setOrderDate(orderDate);
        order.setDeliveryDate(deliveryDate);
        order.setUserId(user.getUserId());
        order.setStatus(false);
        orderRepository.save(order);
    }
    private Date calculateDeliveryDate(Date orderDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderDate);
        cal.add(Calendar.DAY_OF_MONTH, 3);
        return cal.getTime();
    }
}