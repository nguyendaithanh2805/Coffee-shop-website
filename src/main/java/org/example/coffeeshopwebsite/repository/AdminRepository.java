package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Admin;

public interface AdminRepository {
    int save(Admin admin);

    int getUserIdLast();
    String findByName(int id);
}
