package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Admin;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void addAdmin(User user) {
        try {
            Admin admin = new Admin();
            int getUserId = adminRepository.getUserIdLast();
            String checkAdmin = adminRepository.findByName(getUserId);
            if (checkAdmin.equalsIgnoreCase("admin")) {
                admin.setUserId(getUserId);
                admin.setFullName("");
                adminRepository.save(admin);
                logger.info("Save admin successfully with userId " + getUserId);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }
}
