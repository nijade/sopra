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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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
        Quiz chosenQuiz = quizService.findChosenQuiz();
        model.addAttribute("chosenQuiz", chosenQuiz);
        return "quiz";
    }

    @GetMapping("/showQuiz/Results")
    public String showQuizResults(Model model,
                                  @RequestParam("frage1") String answerQuestionOne,
                                  @RequestParam("frage2") String answerQuestionTwo,
                                  @RequestParam("frage3") String answerQuestionThree){

        Quiz chosenQuiz = quizService.findChosenQuiz();

        String rightAnswerQuestionOne = chosenQuiz.getRightAnswersQuestionOne();
        String rightAnswerQuestionTwo = chosenQuiz.getRightAnswersQuestionTwo();
        String rightAnswerQuestionThree = chosenQuiz.getRightAnswersQuestionThree();

        boolean questionOneResult = false;
        boolean questionTwoResult = false;
        boolean questionThreeResult = false;

        if(rightAnswerQuestionOne.equals(answerQuestionOne)){
            questionOneResult = true;
        }
        if(rightAnswerQuestionTwo.equals(answerQuestionTwo)){
            questionTwoResult = true;
        }
        if(rightAnswerQuestionThree.equals(answerQuestionThree)){
            questionThreeResult = true;
        }

        model.addAttribute("rightAnswerQuestionOne", rightAnswerQuestionOne);
        model.addAttribute("rightAnswerQuestionTwo", rightAnswerQuestionTwo);
        model.addAttribute("rightAnswerQuestionThree", rightAnswerQuestionThree);
        model.addAttribute("answerQuestionOne", answerQuestionOne);
        model.addAttribute("answerQuestionTwo", answerQuestionTwo);
        model.addAttribute("answerQuestionThree", answerQuestionThree);
        model.addAttribute("questionOneResult", questionOneResult);
        model.addAttribute("questionTwoResult", questionTwoResult);
        model.addAttribute("questionThreeResult", questionThreeResult);
        model.addAttribute("chosenQuiz", chosenQuiz);

        int rightAnswers = 0;
        if(questionOneResult){rightAnswers++;}
        if(questionTwoResult){rightAnswers++;}
        if(questionThreeResult){rightAnswers++;}

        userService.addXp(userService.getCurrentUser(), rightAnswers * 20);


        return "quizResults";
    }
}
