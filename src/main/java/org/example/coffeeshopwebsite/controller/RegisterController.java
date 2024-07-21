package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.AdminService;
import org.example.coffeeshopwebsite.service.CustomerService;
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
    private final AdminService adminService;
    private final CustomerService customerService;

    @Autowired
    public RegisterController(UserService userService, AdminService adminService, CustomerService customerService) {
        this.userService = userService;
        this.adminService = adminService;
        this.customerService = customerService;
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
            userService.addUser(user);
            adminService.addAdmin(user);
            customerService.addCustomer(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/register";
        }
    }
}
