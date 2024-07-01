package com.example.sopra.repository;

import com.example.sopra.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {




    @Query("SELECT q FROM Quiz q WHERE q.quizID = :quizID")
    Quiz findByQuizID(@Param("quizID") Integer quizID);

    @Query("SELECT q FROM Quiz q")
    List<Quiz> findAll();

    @Query("SELECT q FROM Quiz q WHERE q.isChosen = true")
    Quiz findChosenQuiz();

    @Modifying
    @Transactional
    @Query("UPDATE Quiz q SET q.isChosen = :state WHERE q.quizID = :quizID")
    void setIsChosen(@Param("quizID") Integer quizID, @Param("state") boolean state);
}
