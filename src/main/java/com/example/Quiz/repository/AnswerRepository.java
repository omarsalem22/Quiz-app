package com.example.Quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quiz.models.Answer;
import com.example.Quiz.models.Score;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
List<Answer> findByUserIdAndQuizId(Long userId,Long quizId);

}
