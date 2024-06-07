package com.example.sopra.controller;

import com.example.sopra.entity.Plant;
import com.example.sopra.service.ImageService;
import com.example.sopra.service.PlantService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PlantController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private ImageService imageService;


    @GetMapping("/create")
    public String showCreate(Model model) {
        List<String> photos = imageService.getImageNames();
        model.addAttribute("photos", photos);
        return "create";
    }

    @GetMapping("/searchPlants")
    public String searchResults(@RequestParam String title, Model model) {
        List<Plant> plants = plantService.searchPlantsByTitleContainingIgnoreCase(title);
        model.addAttribute("plants", plants);
        model.addAttribute("searchTerm", title);
        return "searchPlants";
    }

    @PostMapping("/create")
    public String createPlant(@RequestParam String title,
                              @RequestParam(required = false) List<String> photos,
                              @RequestParam Integer height,
                              @RequestParam Double price,
                              @RequestParam(required = false) Boolean hasPlanter,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Double potCircumference,
                              @RequestParam(required = false) Double plantCircumference,
                              @RequestParam(required = false) String tags,
                              Model model) {
        return plantService.createPlant(title, photos, height, price, hasPlanter, description, potCircumference, plantCircumference, tags, model);
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Plant plant = plantService.findPlantByID(id);
        model.addAttribute("plant", plant);
        List<String> photos = imageService.getImageNames();
        model.addAttribute("photos", photos);
        return "editPlant";
    }

    @PostMapping("/edit/{id}")
    public String updatePlant(@PathVariable int id,
                              @RequestParam String title,
                              @RequestParam(required = false) List<String> photos,
                              @RequestParam Integer height,
                              @RequestParam Double price,
                              @RequestParam(required = false) Boolean hasPlanter,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Double potCircumference,
                              @RequestParam(required = false) Double plantCircumference,
                              @RequestParam(required = false) String tags,
                              Model model) {
        return plantService.updatePlant(id, title, photos, height, price, hasPlanter, description, potCircumference, plantCircumference, tags, model);
    }

    @GetMapping("/plants/{id}")
    public String showPlant(@PathVariable int id, Model model){
        Plant plant = plantService.findPlantByID(id);
        model.addAttribute("plant", plant);
        List<String> photos =imageService.getImageNames();
        model.addAttribute("photos", photos);
        return "advert";
    }
}
