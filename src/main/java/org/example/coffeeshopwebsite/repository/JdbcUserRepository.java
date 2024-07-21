package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    private static final class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setRoleId(rs.getInt("roleId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    @Override
    public int save(User user) {
        checkExistsUser(user);
        return jdbcTemplate.update("INSERT INTO tbl_user (roleId, username, password) VALUES (?, ?, ?)", user.getRoleId(), user.getUsername(), encodePassword(user));
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE tbl_uset SET username = ?, password = ?", user.getUsername(), encodePassword(user));
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_user WHERE username = ?", new UserRowMapper(), username);
    }

    private String encodePassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }
    private void checkExistsUser(User user) {
        String checkUserQuery = "SELECT COUNT(*) FROM tbl_user WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(checkUserQuery, new Object[]{user.getUsername()}, Integer.class);
        if (count != null && count > 0)
            throw new IllegalArgumentException("User already exists");
    }
}
