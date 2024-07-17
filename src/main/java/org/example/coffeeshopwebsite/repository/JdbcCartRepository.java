package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            cart.setCartId(rs.getInt("cart_id"));
            cart.setUserId(rs.getInt("user_id"));
            cart.setProductId(rs.getInt("product_id"));
            cart.setQuantity(rs.getInt("quantity"));
            cart.setSellingPrice(rs.getDouble("selling_price"));
            cart.setTotalBill(rs.getDouble("total_bill"));
            return cart;
        }
    }

    @Override
    public int save(Cart cart) {
        return jdbcTemplate.update("INSERT INTO tbl_cart (user_id, product_id, quantity, selling_price, total_bill) VALUES (?, ?, ?, ?, ?)",
                cart.getUserId(), cart.getProductId(),
                cart.getQuantity(), cart.getSellingPrice(), cart.getTotalBill());
    }
}
