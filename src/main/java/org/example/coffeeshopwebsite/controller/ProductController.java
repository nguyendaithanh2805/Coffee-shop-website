package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.example.coffeeshopwebsite.service.FileUploadService;
import org.example.coffeeshopwebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final FileUploadService fileUploadService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(FileUploadService fileUploadService, ProductService productService, CategoryService categoryService) {
        this.fileUploadService = fileUploadService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // READ
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        // Trich xuat ten danh muc san pham tuong ung voi tung san pham
        List<String> categoryNames = products.stream()
                .map(product -> categoryService.findCategoryById(product.getCategoryId()).getName())
                .toList();
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryNames);
        return "admin/products";
    }

    // CREATE
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/add-product";
    }

    @PostMapping("/add")
    public String addProduct(Product product, @ModelAttribute("categoryId") int categoryId, @RequestParam("imageFile") MultipartFile file) {
        product.setCategoryId(categoryId);
        fileUploadService.handleImageUpload(product, file);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/edit-product";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, Product product, @ModelAttribute("categoryId") int categoryId, @RequestParam("imageFile") MultipartFile file) {
        product.setProductId(id);
        product.setCategoryId(categoryId);
        fileUploadService.handleImageUpload(product, file);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
