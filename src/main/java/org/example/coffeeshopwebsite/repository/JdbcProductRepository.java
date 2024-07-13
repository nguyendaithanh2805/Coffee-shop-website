package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getInt("product_id"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setUserId(rs.getInt("user_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setDiscount(rs.getBigDecimal("discount"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setSellingPrice(rs.getBigDecimal("selling_price"));
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_product", new ProductRowMapper());
    }

    @Override
    public Product findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_product WHERE product_id = ?", new ProductRowMapper(), id);
    }

    @Override
    public int save(Product product) {
        return jdbcTemplate.update("INSERT INTO tbl_product (category_id, user_id, name, description, discount, image, quantity, selling_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                product.getCategoryId(), product.getUserId(),
                product.getName(), product.getDescription(),
                product.getDiscount(), product.getImage(),
                product.getQuantity(), product.getSellingPrice());
    }

    @Override
    public int update(Product product) {
        return jdbcTemplate.update("UPDATE tbl_product SET category_id = ?, user_id = ?, name = ?, description = ?, discount = ?, image = ?, quantity = ?, selling_price = ? WHERE product_id = ?",
                product.getCategoryId(), product.getUserId(),
                product.getName(), product.getDescription(),
                product.getDiscount(), product.getImage(),
                product.getQuantity(), product.getSellingPrice(), product.getProductId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM tbl_product WHERE product_id = ?", id);
    }
}
