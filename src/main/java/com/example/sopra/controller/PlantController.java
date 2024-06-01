package com.example.sopra.controller;

import com.example.sopra.entity.Plant;
import com.example.sopra.service.PlantService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PlantController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @GetMapping("/create")
    public String showCreate(Model model) {
        return "create";
    }

    @GetMapping("/searchPlants")
    public String searchResults(@RequestParam String title, Model model) {
        List<Plant> plants = plantService.searchPlantsByTitle(title);
        model.addAttribute("plants", plants);
        model.addAttribute("searchTerm", title);
        return "searchPlants";
    }
}
