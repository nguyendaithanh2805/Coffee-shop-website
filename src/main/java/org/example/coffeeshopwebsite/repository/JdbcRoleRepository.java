package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRoleRepository implements RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final static class RoleRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getInt("role_id"));
            role.setName(rs.getString("name"));
            return role;
        }
    }
    @Override
    public List<Role> getUserRole() {
        return jdbcTemplate.query("SELECT * FROM tbl_role r INNER JOIN tbl_user u ON r.role_id = u.role_id", new RoleRowMapper());
    }

    @Override
    public Integer saveRole(User user) {
        String roleName = user.getUsername();
        String roleIdQuery = "INSERT INTO tbl_role (name) VALUES (?)";
        if (roleName.equalsIgnoreCase("admin"))
            jdbcTemplate.update(roleIdQuery, "ROLE_ADMIN");
        else
            jdbcTemplate.update(roleIdQuery, "ROLE_USER");

        // Lay roleId vua moi luu de gan cho user
        String roleIdRetrieveQuery = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(roleIdRetrieveQuery, Integer.class);
    }
}
