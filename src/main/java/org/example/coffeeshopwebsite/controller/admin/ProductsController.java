package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Categories;
import org.example.coffeeshopwebsite.model.Products;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.example.coffeeshopwebsite.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductsController {
    private final ProductsService productsService;

    private final CategoryService categoryService;

    @Autowired
    public ProductsController(ProductsService productsService, CategoryService categoryService) {
        this.productsService = productsService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products-list")
    public String getAllProducts(Model model) {
        List<Products> productsList = productsService.getAllProduct();
        model.addAttribute("products", productsList);
        return "admin/products";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        List<Categories> categoriesList = categoryService.getAllCategory();
        model.addAttribute("categories", categoriesList);
        model.addAttribute("product", new Products());
        return "admin/product-form";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") Products products, @RequestParam("imageFile") MultipartFile file, @RequestParam("category") Long catId, RedirectAttributes redirectAttributes) {
        Categories category = categoryService.getCategoryById(catId);
        String fileName = file.getOriginalFilename();
        String uploadDir = "D:\\workspace\\java\\Coffee-shop-website\\src\\main\\resources\\static\\user\\images";
        Path uploadPath = Paths.get(uploadDir);
        try {
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName);

            if (!(Files.exists(filePath)))
                Files.copy(file.getInputStream(), filePath);
            products.setProductImage(fileName);
            products.setCategories(category);
            productsService.saveProduct(products);
            redirectAttributes.addFlashAttribute("Message", "Image uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Error", "Failed to upload image");
        }
        return "redirect:/admin/products-list";
    }


    @GetMapping("/edit-product")
    public String showFormForUpdate(@RequestParam Long id, Model model) {
        List<Categories> categoriesList = categoryService.getAllCategory();
        Products product = productsService.getProductById(id);
        model.addAttribute("categories", categoriesList);
        model.addAttribute("product", product);
        return "admin/update-product-form";
    }

    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam Long id) {
        productsService.deleteProductById(id);
        return "redirect:/admin/products-list";
    }
}
