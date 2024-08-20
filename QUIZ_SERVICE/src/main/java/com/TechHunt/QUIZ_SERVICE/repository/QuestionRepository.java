package com.TechHunt.QUIZ_SERVICE.repository;

import com.TechHunt.QUIZ_SERVICE.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findByQuizId(Long quizId);
}
