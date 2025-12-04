package com.example.Quiz.services;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Quiz.Dtos.SubmissionRequest;
import com.example.Quiz.Dtos.SubmissionRequest.AnswerChoice;
import com.example.Quiz.models.Answer;
import com.example.Quiz.models.Question;
import com.example.Quiz.models.Quiz;
import com.example.Quiz.models.Score;
import com.example.Quiz.models.User;
import com.example.Quiz.repository.AnswerRepository;
import com.example.Quiz.repository.QuestionRepository;
import com.example.Quiz.repository.QuizRepository;
import com.example.Quiz.repository.ScoreRepository;


import jakarta.transaction.Transactional;

@Service
public class QuizScoringService {
private final QuizRepository quizRepository; 
private final  QuestionRepository questionRepository;
private final  AnswerRepository answerRepository;
private final ScoreRepository scoreRepository;
private final QuizSessionService sessionService;
public QuizScoringService(QuizRepository quizRepository, QuestionRepository questionRepository,
        AnswerRepository answerRepository, ScoreRepository scoreRepository, QuizSessionService sessionService) {
    this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
    this.scoreRepository = scoreRepository;
    this.sessionService = sessionService;
}
@Transactional

public Score submiAnswers(Long quizId, Long userId, SubmissionRequest request){

    sessionService.validateAndEndSession(userId, quizId);
    Quiz quiz=quizRepository.findById(quizId).orElseThrow(()->new RuntimeException("Quiz not found"));
    User user = new User(); 
    user.setId(userId);
if(!answerRepository.findByUserIdAndQuizId(userId, quizId).isEmpty()){
    throw new RuntimeException("Quiz already submitted by this user.");
}
    List<Question> questions=questionRepository.findByQuizId(quizId);
    Map<Long,Question> questionMap=questions.stream()
    .collect(Collectors.toMap(Question::getId, Function.identity()));
    int correctCount = 0;
    for(AnswerChoice choice : request.getAnswers()){
        Question q=questionMap.get(choice.getQuestionId());

        if(q !=null){
            if(choice.getSelectedOption()!=null && choice.getSelectedOption().equals(q.getCorrectOption())){
                correctCount++;
            }
            // b. Persist Answer Record
            Answer answer=new Answer();
            answer.setUser(user);
            answer.setQuiz(quiz);
            answer.setQuestion(q);
            answer.setSelectedOption(choice.getSelectedOption());
            answerRepository.save(answer);
        }

        // 4. Persist Final Score Record
        

    }
    double scoreValue = (double) correctCount / questions.size();
    Score score = new Score();
        score.setQuiz(quiz);
        score.setUser(user);
        score.setScoreValue(scoreValue);    
        return scoreRepository.save(score);
}

}
