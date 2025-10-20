package com.example.Quiz.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Quiz.Dtos.UserDto;
import com.example.Quiz.models.Role;
import com.example.Quiz.models.User;
import com.example.Quiz.repository.RoleRepository;
import com.example.Quiz.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>());

    }

    @Transactional
    public void addRoleToUser(String username, String rolename) {
        if (!rolename.equals("STUDENT") && !rolename.equals("ADMIN") && rolename.equals("INSTRUCTOR")) {
            throw new IllegalArgumentException("Invalid role. Allowed roles: STUDENT, INSTRUCTOR, ADMIN");
        }
        User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Role role = roleRepository.findByName(rolename);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }
        if (role == null) {
            throw new RuntimeException("Role not found: " + rolename);
        }
        user.addRole(role);
        userRepository.save(user);
    }

}
