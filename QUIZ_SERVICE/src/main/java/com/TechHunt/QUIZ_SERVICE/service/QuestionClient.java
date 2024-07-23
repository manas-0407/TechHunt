package com.TechHunt.QUIZ_SERVICE.service;

import com.TechHunt.QUIZ_SERVICE.models.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE", url = "http://localhost:8088")
//@FeignClient(name = "QUESTION_SERVICE")
public interface QuestionClient {

    @GetMapping("/question/quiz/{quizId}")
    List<Question> getQuestionOfQuiz(@PathVariable Long quizId);
}