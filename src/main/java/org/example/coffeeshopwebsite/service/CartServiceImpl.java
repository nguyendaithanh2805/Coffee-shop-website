package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.CartRepository;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(Cart cart, Product product, int productId, int quantity) {
        try {
            Cart existingCart = cartRepository.findByProductId(productId);
            if (existingCart.getProduct().getProductId() > 0) {
                existingCart.setCartQuantity(existingCart.getCartQuantity() + quantity);
                existingCart.setTotalBill(product.getSellingPrice() * existingCart.getCartQuantity());
                cartRepository.update(existingCart);
                logger.info("Update Cart successfully");
            } else {
                User user = userService.getCurrentUser();
                cart.setUserId(user.getUserId());
                cart.setProductId(productId);
                cart.setCartQuantity(quantity);
                cart.setTotalBill(product.getSellingPrice() * quantity);
                cartRepository.save(cart);
                logger.info("Save cart successfully");
            }
        } catch (EmptyResultDataAccessException e) {
            User user = userService.getCurrentUser();
            cart.setUserId(user.getUserId());
            cart.setProductId(productId);
            cart.setCartQuantity(quantity);
            cart.setTotalBill(product.getSellingPrice() * quantity);
            cartRepository.save(cart);
            logger.info("Save cart successfully");
        }
    }

    @Override
    public List<Cart> getAllProductByCart() {
        int userId = userService.getCurrentUser().getUserId();
        return cartRepository.findAllProductByCart(userId);
    }

    @Override
    public void deleteProductInCartById(int id) {
        cartRepository.deleteById(id);
        logger.info("Deleted successfully");
    }
}
