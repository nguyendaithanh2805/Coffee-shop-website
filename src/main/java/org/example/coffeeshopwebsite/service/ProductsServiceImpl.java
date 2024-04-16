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
}
