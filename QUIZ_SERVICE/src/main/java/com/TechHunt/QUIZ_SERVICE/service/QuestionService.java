package com.TechHunt.QUIZ_SERVICE.service;


import com.TechHunt.QUIZ_SERVICE.models.Question;
import com.TechHunt.QUIZ_SERVICE.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question create(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> get() {
        return questionRepository.findAll();
    }

    public Question getOne(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found !!"));
    }

    public List<Question> getQuestionsOfQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}