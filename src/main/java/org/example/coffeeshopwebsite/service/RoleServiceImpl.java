package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public int addRole(User user) {
        return roleRepository.saveRole(user);
    }
}
