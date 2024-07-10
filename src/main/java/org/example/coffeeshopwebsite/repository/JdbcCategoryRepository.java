package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCategoryRepository implements CategoryRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class CategoryRowMapper implements RowMapper<Category>  {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getInt("category_id"));
            category.setName(rs.getString("name"));
            return category;
        }
    }



    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_category", new CategoryRowMapper());
    }

    @Override
    public Category findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_category WHERE category_id = ?", new CategoryRowMapper(), id);
    }

    @Override
    public int save(Category category) {
        return jdbcTemplate.update("INSERT INTO tbl_category (name) VALUES (?)", category.getName());
    }

    @Override
    public int update(Category category) {
        return jdbcTemplate.update("UPDATE tbl_category SET name = ? WHERE category_id = ?", category.getName(), category.getCategoryId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM tbl_category WHERE category_id = ?", id);
    }
}
