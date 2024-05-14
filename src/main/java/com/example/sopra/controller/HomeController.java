package com.example.sopra.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /**
     * Zeigt die Startseite der Anwendung
     * @param model enthält alle ModelAttribute
     * @return home-Seite
     */
    @GetMapping("/")
    public String showHome(Model model){
        model.addAttribute("message", "Und hier sehen Sie ein ModelAttribut");
        return "home";
    }
}

// Commit Test Nils
