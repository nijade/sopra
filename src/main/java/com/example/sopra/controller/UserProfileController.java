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
    public String saveProfile(@ModelAttribute("user") User updatedUser) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (updatedUser.getName() != null && !updatedUser.getName().isEmpty()) {
            currentUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            currentUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getAge() != null) {
            currentUser.setAge(updatedUser.getAge());
        }
        if (updatedUser.getGender() != null && !updatedUser.getGender().isEmpty()) {
            currentUser.setGender(updatedUser.getGender());
        }
        if (updatedUser.getProfileDescription() != null && !updatedUser.getProfileDescription().isEmpty()) {
            currentUser.setProfileDescription(updatedUser.getProfileDescription());
        }
        return "redirect:/profile/viewProfile";
    }
}
