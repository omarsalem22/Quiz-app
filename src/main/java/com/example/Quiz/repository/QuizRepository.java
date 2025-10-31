package com.example.Quiz.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quiz.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByCreatedbyId(Long userId ,Pageable pageable);

    List<Quiz> findByApproved(boolean approved );

}
