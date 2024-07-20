package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.service.CartService;
import org.example.coffeeshopwebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }
    @GetMapping
    public String listCart(Model model) {
        List<Cart> carts = cartService.getAllProductByCart();
        model.addAttribute("cartItems", carts);
        return "user/carts";
    }
    @PostMapping("/add")
     public String addToCart(@RequestParam int productId, @RequestParam int quantity, Cart cart) {
        Product product = findProductByProductIdInCart(productId);
        saveCart(cart, product, productId, quantity);
        return "redirect:/menu";
    }
    private void saveCart(Cart cart, Product product, int productId, int quantity) {
        cartService.addProductToCart(cart, product, productId, quantity);
    }
    private Product findProductByProductIdInCart(int productId) {
        return productService.findProductById(productId);
    }
}
