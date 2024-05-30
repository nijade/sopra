package com.example.sopra.controller;

import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PlantController {

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String showCreate(Model model){
        return "create";
    }

}
