package com.example.Quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.services.UserService;
import com.example.Quiz.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager ;
    @Autowired
    UserService userService ;
    @Autowired
    JwtUtil jwtUtil;
    

}
