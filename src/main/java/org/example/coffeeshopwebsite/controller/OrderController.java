package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.model.Order;
import org.example.coffeeshopwebsite.model.Payment;
import org.example.coffeeshopwebsite.service.OrderService;
import org.example.coffeeshopwebsite.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    @Autowired
    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }
    // CREATE
    @GetMapping("/orders/checkout")
    public String showOrderForm(Model model) {
        List<Payment> payments = paymentService.getAllPaymentMethod();
        model.addAttribute("order", new Order());
        model.addAttribute("payments", payments);
        return "user/order";
    }

    @PostMapping("/orders/add")
    public String checkOut(@RequestParam("paymentId") int paymentId, @ModelAttribute Order order) {
        order.setPaymentId(paymentId);
        orderService.saveOrder(order);
        return "redirect:/user/success";
    }

    // READ
    @GetMapping("/admin/orders")
    public String listOrder(Model model) {
        model.addAttribute("orders", orderService.getAllOrder());
        return "admin/orders";
    }

    // UPDATE
    @PostMapping("/admin/orders/edit-status")
    public String updateStatus(@RequestParam int id, @RequestParam(name = "status", required = false) Boolean status) {
        Order order = orderService.getOrderById(id);
        order.setStatus(status);
        orderService.updateOrder(order);
        return "redirect:/admin/orders";
    }
    // DELETE
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        orderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }
}
