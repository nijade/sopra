package com.example.sopra.Scheduler;

import com.example.sopra.entity.Quiz;
import com.example.sopra.service.QuizService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
@Component
public class Scheduler {

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;


    // @Scheduled(fixedRate = 120000)   // Setting for testing
    @Scheduled(cron = "0 0 0 * * MON") // Setting for deployment
    public void scheduledQuiz(){
        List<Quiz> quizzes = quizService.findAll();

        // Findet das aktuelle Quiz, das als true markiert ist
        Quiz currentChosenQuiz = quizzes.stream()
                .filter(Quiz::getIsChosen)
                .findFirst()
                .orElse(null);

        if (currentChosenQuiz != null) {
            // Generiert einen zufälligen neuen Index für das neue Quiz
            int newIndex = indexGenerator(quizzes.indexOf(currentChosenQuiz), quizzes.size());
            Quiz newChosenQuiz = quizzes.get(newIndex);

            // Setzt das aktuelle Quiz auf false
            quizService.setIsChosen(currentChosenQuiz.getQuizID(), false);

            // Setzt das neue zufällige Quiz auf true
            quizService.setIsChosen(newChosenQuiz.getQuizID(), true);
        }
    }

    // Methode um einen zufälligen Index mit Exklusion des aktuellen durch Rekursion zu produzieren.
    public Integer indexGenerator(Integer avoidIndex, Integer maxSize) {
        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(maxSize);
        } while (randomIndex == avoidIndex);

        return randomIndex;
    }
}
