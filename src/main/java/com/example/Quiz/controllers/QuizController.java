package com.example.Quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.models.Quiz;
import com.example.Quiz.services.QuizService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/createQuiz/{instructorId}")
    public Quiz createQuiz(@RequestBody Quiz quiz, @PathVariable Long instructorId) {
        return quizService.createQuiz(quiz, instructorId);
    }

    @GetMapping("/instructor/{instructorId}")

    public List<Quiz> getQuizzesByInstructor(@PathVariable Long instructorId, Pageable pageable) {
        return quizService.findQuizByInstructor(instructorId, pageable);

    }

    @GetMapping("")
    public List<Quiz> getApprovedQuizzes() {
        return quizService.findApprovedQuizzes();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')or hasRole('ADMIN')")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        return ResponseEntity.ok(quizService.updateQuiz(id, updatedQuiz));
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public  ResponseEntity<Void> deleteQuiz(@PathVariable Long id){
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

}
