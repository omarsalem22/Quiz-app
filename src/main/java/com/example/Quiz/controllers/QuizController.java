package com.example.Quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.models.Quiz;
import com.example.Quiz.services.QuizService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/createQuiz/{instructorId}")
    public Quiz createQuiz(@RequestBody Quiz quiz ,@PathVariable Long instructorId){
        return quizService.createQuiz(quiz, instructorId);
    }

}
