package com.example.sopra.controller;

import com.example.sopra.entity.Plant;
import com.example.sopra.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PlantService plantService;

    /**
     * Zeigt die Startseite der Anwendung
     * @param model enthält alle ModelAttribute
     * @return home-Seite
     */
    @GetMapping("/")
    public String showHome(Model model){
        List<Plant> plants = plantService.findAllPlants();
        model.addAttribute("message", "Und hier sehen Sie ein ModelAttribut");
        model.addAttribute("plants", plants);
        return "home";
    }
}


