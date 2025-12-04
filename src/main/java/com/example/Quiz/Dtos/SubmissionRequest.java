package com.example.Quiz.Dtos;

import java.util.List;

import lombok.Data;

@Data
public class SubmissionRequest {
private List<AnswerChoice> answers;

@Data
    public static class AnswerChoice {
        private Long questionId;
        private Integer selectedOption; 
    }

}