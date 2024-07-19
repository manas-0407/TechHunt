package com.TechHunt.QUESTION_SERVICE.controller;

import com.TechHunt.QUESTION_SERVICE.models.Question;
import com.TechHunt.QUESTION_SERVICE.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/question")
public class Route {

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

    @GetMapping("/quiz")
    public List<Question> getQuestionsOfQuiz(@RequestParam Long quizId) {
        return questionService.getQuestionsOfQuiz(quizId);
    }

}
