package com.TechHunt.QUIZ_SERVICE.service;

import com.TechHunt.QUIZ_SERVICE.models.Question;
import com.TechHunt.QUIZ_SERVICE.models.QuestionDto;
import com.TechHunt.QUIZ_SERVICE.models.Quiz;
import com.TechHunt.QUIZ_SERVICE.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionService questionService;

    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> get() {
        List<Quiz> quizzes = quizRepository.findAll();

        List<Quiz> questionSetQuiz = quizzes
                                        .stream()
                                        .map(quiz -> {

                                            quiz.setQuestionDtos(
                                                    questionService.getQuestionsOfQuiz(quiz.getId())
                                                            .stream()
                                                            .map(question -> {

                                                                return new QuestionDto
                                                                        (question.getQuestionId(),
                                                                                question.getQuestion(),
                                                                                question.getQuizId());

                                                            })
                                                            .toList()
                                            );
                                            return quiz;

                                        })
                                        .toList();

        return questionSetQuiz;
    }

    public Quiz get(Long id) {

        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setQuestionDtos(
                questionService.getQuestionsOfQuiz(quiz.getId())
                        .stream()
                        .map( question -> {
                            return new QuestionDto(question.getQuestionId(), question.getQuestion(), question.getQuizId());
                        })
                        .toList()
        );
        return quiz;
    }
}