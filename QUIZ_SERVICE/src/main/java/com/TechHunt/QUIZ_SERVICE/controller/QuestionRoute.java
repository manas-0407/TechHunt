package com.TechHunt.QUIZ_SERVICE.controller;

import com.TechHunt.QUIZ_SERVICE.models.Question;
import com.TechHunt.QUIZ_SERVICE.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/question")
public class QuestionRoute {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/add")
    public Question create(@RequestBody Question question) {
        return questionService.create(question);
    }

    @GetMapping( value = "/get/all")
    public List<Question> getAll() {
        return questionService.get();
    }

    @GetMapping("/get")
    public Question getAll(@RequestParam Long questionId) {
        return questionService.getOne(questionId);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsOfQuiz(quizId);
    }

}
