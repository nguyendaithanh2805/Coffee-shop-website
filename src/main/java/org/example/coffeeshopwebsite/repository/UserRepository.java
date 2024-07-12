package org.example.coffeeshopwebsite.repository;


import org.example.coffeeshopwebsite.model.User;

public interface UserRepository {
    int save(User user);
    int update(User user);
    User findByUsername(String username);
}
