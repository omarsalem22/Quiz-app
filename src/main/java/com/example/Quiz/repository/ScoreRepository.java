package com.example.Quiz.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quiz.models.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByUserId(Long userId);

    List<Score> findByQuizId(Long quizId);
}