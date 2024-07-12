package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.AdminService;
import org.example.coffeeshopwebsite.service.RoleService;
import org.example.coffeeshopwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final RoleService roleService;
    private final AdminService adminService;

    @Autowired
    public RegisterController(UserService userService, RoleService roleService, AdminService adminService) {
        this.userService = userService;
        this.roleService = roleService;
        this.adminService = adminService;
    }

    @GetMapping
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/add")
    public String addUser(User user, Model model) {
        try
        {
            int roleId = roleService.addRole(user);
            userService.addUser(user, roleId);
            adminService.addAdmin(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/register";
        }
    }
}
