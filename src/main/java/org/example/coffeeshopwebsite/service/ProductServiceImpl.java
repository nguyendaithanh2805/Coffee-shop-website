package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product, User user) {
        try {
            product.setUserId(user.getUserId());
            product.setSellingPrice(product.getSellingPrice() - product.getDiscount());
            productRepository.save(product);
            logger.info("Save product successfully");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void updateProduct(Product product, User user) {
        product.setUserId(user.getUserId());
        productRepository.update(product);
        logger.info("Update product successfully");
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
        logger.info("Delete product successfully");
    }

    @Override
    @Transactional
    public void updateProductQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.update(product);
        logger.info("Update product quantity successfully");
    }
}
