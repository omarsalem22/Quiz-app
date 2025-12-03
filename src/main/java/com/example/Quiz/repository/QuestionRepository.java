package com.example.Quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Quiz.models.Question;

@Repository
public interface  QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByQuizId(Long quizId);


}
