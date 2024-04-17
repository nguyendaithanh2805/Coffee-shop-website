package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Products;
import org.example.coffeeshopwebsite.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService{
    private final ProductsRepository productsRepository;

    @Autowired
    public  ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> getAllProduct() {
        return productsRepository.findAll();
    }

    @Override
    public void saveProduct(Products products) {
        try {
            if (products.getProductId() == null) {
                products.setProductName(products.getProductName());
                products.setDescription(products.getDescription());
                products.setDiscount(products.getDiscount());
                products.setQuantity(products.getQuantity());
                products.setSellingPrice(products.getSellingPrice());
                productsRepository.save(products);
            }
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
    }
}
