package com.example.sopra.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller-Klasse zur Festlegung der globalen Fehlerseite.
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Methode zur Handhabung von Fehlerseiten.
     *
     * @return String der Fehlerseite
     */
   @GetMapping("/error")
    public String handleError() {
        return "error";
    }

}