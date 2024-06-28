package com.example.sopra.service;

import com.example.sopra.entity.Plant;
import com.example.sopra.entity.Quiz;
import com.example.sopra.repository.PlantRepository;
import com.example.sopra.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Quiz saveQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public Quiz findQuizByID(int quizID){
        return quizRepository.findByQuizID(quizID);
    }

    public List<Quiz> findAll(){
        return quizRepository.findAll();
    }
}
