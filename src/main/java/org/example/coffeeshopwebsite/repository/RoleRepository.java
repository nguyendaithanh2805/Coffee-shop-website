package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Role;

public interface RoleRepository {
    Role getRoleByUsername(String username);
}
