package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private final ProductRepository productRepository;

    @Autowired
    public MenuController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listMenu(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "user/menu";
    }
}
