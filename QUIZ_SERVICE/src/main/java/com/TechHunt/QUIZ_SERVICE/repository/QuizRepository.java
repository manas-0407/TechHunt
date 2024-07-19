package com.TechHunt.QUIZ_SERVICE.repository;


import com.TechHunt.QUIZ_SERVICE.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}