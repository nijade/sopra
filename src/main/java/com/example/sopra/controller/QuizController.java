package com.example.sopra.controller;


import com.example.sopra.entity.Quiz;
import com.example.sopra.service.ImageService;
import com.example.sopra.service.PlantService;
import com.example.sopra.service.QuizService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class QuizController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;


    @GetMapping("/showQuiz")
    public String showQuiz(Model model) {
        List<Quiz> quizzes = quizService.findAll();
        model.addAttribute("quizzes", quizzes);
        return "quiz";
    }
}
