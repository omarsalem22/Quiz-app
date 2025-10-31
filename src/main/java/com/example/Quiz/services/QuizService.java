package com.example.Quiz.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Quiz.models.Quiz;
import com.example.Quiz.models.User;
import com.example.Quiz.repository.QuizRepository;

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

    public List<Quiz> findQuizByInstructor(Long instructorID, Pageable pageable) {
        return quizRepository.findByCreatedbyId(instructorID, pageable);
    }

    public List<Quiz> findApprovedQuizzes() {
        return quizRepository.findByApproved(true);
    }

}
