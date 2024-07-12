package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcAdminRepository implements AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class AdminRowMapper implements RowMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setUserId(rs.getInt("user_id"));
            admin.setFullName(rs.getString("full_name"));
            return admin;
        }
    }
    @Override
    public int save(Admin admin) {
        return jdbcTemplate.update("INSERT INTO tbl_admin (user_id, full_name) VALUES (?, ?)", admin.getUserId(), admin.getFullName());
    }

    @Override
    public int getUserIdLast() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    @Override
    public String findByName(int id) {
        return jdbcTemplate.queryForObject("SELECT username FROM tbl_user WHERE user_id = ?", new Object[]{id}, String.class);
    }
}
