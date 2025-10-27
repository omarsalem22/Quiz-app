package com.example.Quiz.Dtos;

import org.antlr.v4.runtime.misc.NotNull;

import com.example.Quiz.models.User.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;

    private String email;
    private Role role;

}
