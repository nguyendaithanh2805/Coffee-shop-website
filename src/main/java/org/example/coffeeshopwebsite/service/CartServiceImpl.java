package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final UserService userService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public void addProductToCart(Cart cart, Product product, int productId, int quantity) {
        User user = userService.getCurrentUser();
        cart.setUserId(user.getUserId());
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setSellingPrice(product.getSellingPrice());
        cart.setTotalBill(product.getSellingPrice() * quantity);
        cartRepository.save(cart);
        logger.info("Save cart successfully");
    }

    @Override
    public List<Cart> getAllProductByCart() {
        int userId = userService.getCurrentUser().getUserId();
        return cartRepository.findAllProductByCart(userId);
    }
}
