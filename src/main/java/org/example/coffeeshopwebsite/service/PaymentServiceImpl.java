package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Payment;
import org.example.coffeeshopwebsite.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPaymentMethod() {
        return paymentRepository.findAll();
    }
}
