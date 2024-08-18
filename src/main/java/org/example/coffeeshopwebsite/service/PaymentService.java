package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPaymentMethod();
}
