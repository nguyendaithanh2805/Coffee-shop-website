package org.example.coffeeshopwebsite.repository;


import org.example.coffeeshopwebsite.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {
    List<User> findAll();
    User findById(int id);
    int save(User user);
    int update(User user);
    int deleteById(int id);

    User findByUserName(String username);
}
