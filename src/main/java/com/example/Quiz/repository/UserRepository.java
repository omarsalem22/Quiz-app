package com.example.Quiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quiz.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional <User> findByEmail(String email);
   Optional <User> findByVerificationcode(String verificationcode);

}

