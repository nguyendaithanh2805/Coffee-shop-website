package org.example.coffeeshopwebsite.service;

import jakarta.transaction.Transactional;
import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;
import org.example.coffeeshopwebsite.repository.RoleRepository;
import org.example.coffeeshopwebsite.repository.UserRepository;
import org.example.coffeeshopwebsite.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void saveRegister(User user, Role role, UserRole userRole) {
        try {
            user.setUsername(user.getUsername());
            logger.info("Saving user: " + user.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            String existingRole = roleRepository.findByName(user.getUsername());
            if (existingRole == null) {
                role.setName(user.getUsername());
                roleRepository.save(role);
                logger.info("Role saved successfully");
            } else
                return;
            userRole.setUser(user);
            userRole.setRole(role);
            logger.info(" userRole saved successfully");
            userRoleRepository.save(userRole);
            logger.info("User saved successfully");
            userRepository.save(user);
        } catch (Exception e) {
            logger.info("Error saving user: " + e.getMessage());
        }
    }
}
