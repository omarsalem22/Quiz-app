package com.example.Quiz.services;

import org.springframework.stereotype.Service;

import com.example.Quiz.models.Quiz;
import com.example.Quiz.models.User;
import com.example.Quiz.repository.QuizRepository;
import com.example.Quiz.repository.UserRepository;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserService userService;

    public QuizService(QuizRepository quizRepository, UserService userService) {
        this.quizRepository = quizRepository;
        this.userService = userService;
    }

    public Quiz createQuiz(Quiz quiz, Long instructorID) {

        User instructor = userService.findById(instructorID);
        if (instructor == null || !instructor.getRole().equals(User.Role.INSTRUCTOR)) {
            throw new IllegalArgumentException("Invalid instructor");
        }
        quiz.setCreatedby(instructor);
        return quizRepository.save(quiz);

    }

}
