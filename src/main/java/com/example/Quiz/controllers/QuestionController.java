package com.example.Quiz.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.Dtos.QuestionRequest;
import com.example.Quiz.models.Question;
import com.example.Quiz.services.QuestionService;

@RestController
@RequestMapping("/api/quizzes")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/{quizId}/questions")
    public ResponseEntity<Question> addQQuestion(@PathVariable Long quizId,
            @RequestBody QuestionRequest questionRequest) {
        Question createdQuestion = questionService.addQuestionToQuiz(quizId, questionRequest);
        return ResponseEntity.ok(createdQuestion);
    }

    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Long quizId) {
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        return ResponseEntity.ok(questions);
    }
}
