package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Products;
import org.example.coffeeshopwebsite.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products-list")
    public String getAllProducts(Model model) {
        List<Products> productsList = productsService.getAllProduct();
        model.addAttribute("products", productsList);
        return "admin/products";
    }
}
