package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Customer;

public interface CustomerRepository {
    int save(Customer customer);

    int getUserIdLast();
    String findByName(int id);
}
