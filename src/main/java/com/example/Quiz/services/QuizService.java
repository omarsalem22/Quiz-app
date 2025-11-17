package com.example.Quiz.services;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Quiz.models.Quiz;
import com.example.Quiz.models.User;
import com.example.Quiz.repository.QuizRepository;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserService userService;
    private final EmailService emailService;

    public QuizService(QuizRepository quizRepository, UserService userService, EmailService emailService) {
        this.quizRepository = quizRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    // private static final Logger logger =
    // LoggerFactory.getLogger(UserService.class);

    @CacheEvict(value="quizzes",key="#root.methodName" )   


    public Quiz createQuiz(Quiz quiz, Long instructorID) {

        User instructor = userService.findById(instructorID);
        if (instructor == null || !instructor.getRole().equals(User.Role.INSTRUCTOR)) {
            throw new IllegalArgumentException("Invalid instructor");
        }
        quiz.setCreatedby(instructor);
        emailService.sendTestEmail("quiz Created",  "successfull  created quiz with title: " + quiz.getTitle());
        System.out.println("successfull  created quiz with title: " + quiz.getTitle());
        return quizRepository.save(quiz);

    }

    public List<Quiz> findQuizByInstructor(Long instructorID, Pageable pageable) {
        return quizRepository.findByCreatedbyId(instructorID, pageable);
    }

    @Cacheable(value = "quizzes", key = "#root.methodName")
    public List<Quiz> findApprovedQuizzes() {
        return quizRepository.findByApproved(true);
        // return quizRepository.findAll();

    }

    public Quiz updateQuiz(Long quizId, Quiz quiz) {
        Quiz existing = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        existing.setTitle(quiz.getTitle());
        existing.setDescription(quiz.getDescription());
        // existing.setApproved(quiz.isApproved());
        return quizRepository.save(existing);

    }

    @CacheEvict(value = "quizzes", key = "#root.methodName")

    public void deleteQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quizRepository.delete(quiz);
    }

}
