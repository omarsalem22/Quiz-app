package com.example.Quiz.Dtos;

import java.util.List;

import lombok.Data;

@Data
public class QuestionRequest {
private String text;
private Integer correctOption;
private  List <String> options;
}
