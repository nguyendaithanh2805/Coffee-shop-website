package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Admin;
import org.example.coffeeshopwebsite.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class CustomerRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setUserId(rs.getInt("userId"));
            customer.setFullName(rs.getString("fullName"));
            return customer;
        }
    }

    @Override
    public int save(Customer customer) {
        return jdbcTemplate.update("INSERT INTO tbl_customer (userId, fullName) VALUES (?, ?)", customer.getUserId(), customer.getFullName());
    }

    @Override
    public int getUserIdLast() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    @Override
    public String findByName(int id) {
        return jdbcTemplate.queryForObject("SELECT username FROM tbl_user WHERE userId = ?", new Object[]{id}, String.class);
    }
}
