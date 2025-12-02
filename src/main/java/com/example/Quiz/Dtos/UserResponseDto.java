package com.example.Quiz.Dtos;

import com.example.Quiz.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Optional but recommended

@JsonIgnoreProperties(ignoreUnknown = true) 
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private User.Role role;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public User.Role getRole() {
        return role;
    }
    
 
}