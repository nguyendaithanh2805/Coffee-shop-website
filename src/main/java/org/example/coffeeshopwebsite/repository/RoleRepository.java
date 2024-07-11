package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;

import java.util.List;

public interface RoleRepository {
    List<Role> getUserRole();
    Integer saveRole(User user);
}
