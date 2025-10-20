package com.example.Quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.repository.UserRepository;
import com.example.Quiz.services.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired

    private UserService userService;

    @PostMapping("/{username}/roles")
    public String addRole(@PathVariable String username, @RequestParam String role) {
        userService.addRoleToUser(username, role);
        return "Role '" + role + "' added to user '" + username + "'";
    }

}
