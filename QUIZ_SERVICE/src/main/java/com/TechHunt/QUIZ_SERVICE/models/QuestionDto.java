package com.TechHunt.QUIZ_SERVICE.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private  Long questionId;
    private  String question;
    private  Long quizId;
}