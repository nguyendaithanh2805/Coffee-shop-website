package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Admin;
import org.example.coffeeshopwebsite.model.Customer;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer(User user) {
        try {
            Customer customer = new Customer();
            int getUserId = customerRepository.getUserIdLast();
            String checkCustomer = customerRepository.findByName(getUserId);
            if (!(checkCustomer.equalsIgnoreCase("admin"))) {
                customer.setUserId(getUserId);
                customer.setFullName("User");
                customerRepository.save(customer);
                logger.info("Save customer successfully with userId " + getUserId);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }
}
