// package com.example.Quiz.services;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.example.Quiz.Dtos.ScoreResponse;
// import com.example.Quiz.models.Score;
// import com.example.Quiz.repository.ScoreRepository;

// @Service
// public class ScoreService {

//     private final ScoreRepository scoreRepository;

//     public ScoreService(ScoreRepository scoreRepository) {
//         this.scoreRepository = scoreRepository;
//     }

//     private ScoreResponse convertToDto(Score score) {
//         ScoreResponse dto = new ScoreResponse();
//         dto.setId(score.getId());
//         dto.setQuizTitle(score.getQuiz().getTitle());
//         dto.setQuizId(score.getQuiz().getId());
//         dto.setUserName(score.getUser().getUsername());
//         dto.setUserId(score.getUser().getId());
//         dto.setScoreValue(score.getScoreValue());
//         dto.setCompletionTime(score.getCompletionTime());
//         return dto;
//     }

//     public List<ScoreResponse> getScoresByUserId(Long userId) {
//         List<Score> scores = scoreRepository.findByUserId(userId);
//         return scores.stream()
//                 .map(this::convertToDto)
//                 .toList();
//     }

//     public List<ScoreResponse> getScoresByQuiz(Long quizId) {
//         List<Score> scores = scoreRepository.findByQuizId(quizId);

//         return scores.stream()
//                 .map(this::convertToDto)
//                 .collect(Collectors.toList());
//     }
// }
