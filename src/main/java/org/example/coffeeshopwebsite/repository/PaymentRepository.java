package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();
}
