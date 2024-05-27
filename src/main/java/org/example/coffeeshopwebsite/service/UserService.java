package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface UserService {
    User findByUsername(String username);
    void saveRegister(User user, Role role, UserRole userRole);
}
