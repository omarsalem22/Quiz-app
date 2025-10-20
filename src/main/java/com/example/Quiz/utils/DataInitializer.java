package com.example.Quiz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.example.Quiz.models.Role;
import com.example.Quiz.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

public class DataInitializer   {
@Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        createRoleIfNotExists("STUDENT");
        createRoleIfNotExists("INSTRUCTOR");
        createRoleIfNotExists("ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName) == null) {
            roleRepository.save(new Role(roleName));
            System.out.println("Created role: " + roleName);
        }
    }

}
