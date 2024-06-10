package com.example.sopra.controller;

import com.example.sopra.entity.User;
import com.example.sopra.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/viewProfile")
    public String viewProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "profile/viewProfile";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "profile/editProfile";
    }

    @PostMapping("/editProfile")
    public String saveProfile(@ModelAttribute("user") User user) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        //currentUser.setPhoto(user.getPhoto());
        currentUser.setAge(user.getAge());
        currentUser.setGender(user.getGender());
        currentUser.setProfileDescription(user.getProfileDescription());
        userService.updateUser(currentUser);
        return "redirect:/profile/viewProfile";
    }
}
