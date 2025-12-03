package com.example.Quiz.Dtos;

import java.util.List;

import lombok.Data;
@Data
public class QuestionResponse {

private Long id;
private String text;
private  List <String> options;

}
