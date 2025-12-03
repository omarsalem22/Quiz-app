package com.example.Quiz.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Quiz.Dtos.QuestionRequest;
import com.example.Quiz.models.Question;
import com.example.Quiz.models.Quiz;
import com.example.Quiz.repository.QuestionRepository;
import com.example.Quiz.repository.QuizRepository;
import com.example.Quiz.models.Option;

@Service
public class QuestionService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuestionService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public Question addQuestionToQuiz(Long quizId, QuestionRequest questionRequest) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        Question question = new Question();
        question.setText(questionRequest.getText());
        question.setQuiz(quiz);
        question.setCorrectOption(questionRequest.getCorrectOption());

        // add options to question
        List<Option> options = questionRequest.getOptions().stream().map(optionText -> {
            Option option = new Option();
            option.setText(optionText);
            option.setQuestion(question);
            return option;
        }).collect(Collectors.toList());
        question.setOptions(options);
        return questionRepository.save(question);

    }

    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
