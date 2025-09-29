package com.example.Quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quiz.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
