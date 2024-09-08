package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.*;
import org.example.coffeeshopwebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    private final OrderDetailService orderDetailService;
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, PaymentService paymentService, OrderDetailService orderDetailService, CartService cartService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.orderDetailService = orderDetailService;
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
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
        User user = userService.getCurrentUser();
        order.setPaymentId(paymentId);
        orderService.saveOrder(order);
        List<Cart> productsInCart= cartService.getProductsInCart(user.getUserId());
        int orderId = order.getOrderId();
        for (Cart cart : productsInCart) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(cart.getProductId());
            orderDetail.setDiscount(cart.getProduct().getDiscount());
            orderDetail.setOrderQuantity(cart.getCartQuantity());
            orderDetail.setTotalBill(cart.getTotalBill());
            orderDetailService.saveOrderDetail(orderDetail);
            DecreaseProductQuantity(cart, orderId);
            DeleteCart(cart.getProductId());
        }
        return "redirect:/menu";
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
    @GetMapping("/admin/orders/delete")
    public String deleteProduct(@RequestParam int id) {
        orderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }

    private void DecreaseProductQuantity(Cart cart, int orderId) {
        List<Product> products = productService.getProductByOrderId(orderId);
        for (Product product : products) {
            product.setQuantity(product.getQuantity() - cart.getCartQuantity());
            User user = userService.getUserIdAdmin();
            productService.updateProduct(product, user);
        }
    }

    private void DeleteCart(int productId) {
        cartService.deleteProductInCartById(productId);
    }
}
