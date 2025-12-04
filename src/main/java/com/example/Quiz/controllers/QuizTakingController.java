package com.example.Quiz.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.Dtos.SubmissionRequest;
import com.example.Quiz.Dtos.UserDto;
import com.example.Quiz.models.Score;
import com.example.Quiz.models.User;
import com.example.Quiz.services.QuizScoringService;
import com.example.Quiz.services.QuizSessionService;
import com.example.Quiz.services.UserService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizTakingController {
    private final QuizSessionService quizSessionService;
    private final QuizScoringService quizScoringService;
    private final UserService userService;

    public QuizTakingController(QuizSessionService quizSessionService, QuizScoringService quizScoringService,
            UserService userService) {
        this.quizSessionService = quizSessionService;
        this.quizScoringService = quizScoringService;
        this.userService = userService;
    }

    private User getCurrentUser(Principal principal) {
        String username = principal.getName();

        return userService.findUserEntityByUsername(username);

    }

    @PostMapping("/{quizId}/start")
    public ResponseEntity<String> startQuiz(@PathVariable Long quizId, Principal principal) {

        User user = getCurrentUser(principal);
        Long userId = user.getId();
        quizSessionService.startSession(userId, quizId);
        return ResponseEntity.ok("Quiz session started for user ID: " + userId + ".");

    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<Score> submitQuiz(@PathVariable Long quizId,
            @RequestBody SubmissionRequest request,
            Principal principal) {
        User user = getCurrentUser(principal);
        Long userId = user.getId();
        Score finalScore = quizScoringService.submiAnswers(quizId, userId, request);
        return ResponseEntity.ok(finalScore);
    }
}