package org.example.coffeeshopwebsite.controller.user;

import org.example.coffeeshopwebsite.model.Products;
import org.example.coffeeshopwebsite.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MenuController {
    private final ProductsService productsService;

    @Autowired
    public MenuController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/menu")
    public String getProducts(Model model) {
        List<Products> productsList = productsService.getAllProduct();
        model.addAttribute("products", productsList);
        return "user/menu";
    }
}
