package com.example.Quiz.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.Dtos.UserResponseDto;
import com.example.Quiz.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)    @PreAuthorize("hasRole('INSTRUCTOR')")
    public UserResponseDto getUser(@PathVariable("id") Long userId) {

        return userService.findUserById(userId);

    }

}
