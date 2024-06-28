package com.example.sopra.repository;

import com.example.sopra.entity.Plant;
import com.example.sopra.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {




    @Query("SELECT q FROM Quiz q WHERE q.quizID = :quizID")
    Quiz findByQuizID(@Param("quizID") Integer quizID);

    @Query("SELECT q FROM Quiz q")
    List<Quiz> findAll();
}
