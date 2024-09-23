package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCartRepository implements CartRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class CartRowMapper implements RowMapper<Cart> {

        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart cart = new Cart();
            cart.setCartId(rs.getInt("cartId"));
            cart.setUserId(rs.getInt("userId"));
            cart.setProductId(rs.getInt("productId"));
            cart.setCartQuantity(rs.getInt("cartQuantity"));
            cart.setTotalBill(rs.getDouble("totalBill"));

            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setUserId(rs.getInt("userId"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setDiscount(rs.getDouble("discount"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setSellingPrice(rs.getDouble("sellingPrice"));
            cart.setProduct(product);
            return cart;
        }
    }

    @Override
    public int save(Cart cart) {
        return jdbcTemplate.update("INSERT INTO tbl_cart (userId, productId, cartQuantity, totalBill) VALUES (?, ?, ?, ?)",
                cart.getUserId(), cart.getProductId(),
                cart.getCartQuantity(), cart.getTotalBill());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM tbl_cart WHERE productId = ?", id);
    }

    @Override
    public int deleteByIdAndUserId(int id, int userId) {
        return  jdbcTemplate.update("DELETE FROM  tbl_cart c WHERE c.productId =? and c.userId = ?", id, userId);
    }

    @Override
    public List<Cart> findAllProductByCart(int userId) {
        return jdbcTemplate.query("SELECT * FROM tbl_cart c INNER JOIN tbl_product p ON c.productId = p.productId WHERE c.userId = ?", new CartRowMapper(), userId);
    }

    @Override
    public Cart findByProductId(int productId) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_cart c INNER JOIN tbl_product p ON c.productId = p.productId WHERE c.productId = ?", new CartRowMapper(), productId);
    }

    @Override
    public void update(Cart existingCart) {
        jdbcTemplate.update("UPDATE tbl_cart SET userId = ?, productId = ?, cartQuantity = ?, totalBill = ? WHERE cartId = ?",
                existingCart.getUserId(), existingCart.getProductId(),
                existingCart.getCartQuantity(), existingCart.getTotalBill(), existingCart.getCartId());
    }

    @Override
    public List<Cart> findProductsInCart(int userId) {
        return jdbcTemplate.query("SELECT * FROM tbl_cart c INNER JOIN tbl_product p ON c.productId = p.productId WHERE c.userId = ?", new CartRowMapper(), userId);
    }

    @Override
    public Cart findByProductIdAndUserId(int productId, int userId) {
        return  jdbcTemplate.queryForObject("SELECT * FROM tbl_cart c WHERE c.productId = ? and c.userId = ?", new CartRowMapper(), productId, userId);
    }
}
