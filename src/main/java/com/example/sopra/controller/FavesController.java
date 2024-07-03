package com.example.sopra.controller;

import com.example.sopra.entity.User;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavesController {

    @Autowired
    private UserService userService;

    @GetMapping("/faves")
    public String showWishlist(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "faves";
    }
}
