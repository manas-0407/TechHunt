package com.TechHunt.QUIZ_SERVICE.controller;
import com.TechHunt.QUIZ_SERVICE.models.Quiz;
import com.TechHunt.QUIZ_SERVICE.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizRoute {

    @Autowired
    private QuizService quizService;

    @PostMapping(value = "/create")
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz) {
        System.err.println("Here");
        return ResponseEntity.ok().body(quizService.add(quiz));
    }

    @GetMapping(value = "/get/all")
    public List<Quiz> get() {
        return quizService.get();
    }

    @GetMapping("/get")
    public ResponseEntity<Quiz> getOne(@RequestParam Long quizId) {
        return ResponseEntity.ok().body(quizService.get(quizId));
    }
}