package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcRoleRepository implements RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class RoleRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getInt("roleId"));
            role.setName(rs.getString("name"));
            return role;
        }
    }
    @Override
    public Role getRoleByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_role r INNER JOIN tbl_user u ON r.roleId = u.roleId WHERE u.username = ?", new Object[]{username}, new RoleRowMapper());
    }
}
