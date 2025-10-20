package com.example.Quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quiz.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
