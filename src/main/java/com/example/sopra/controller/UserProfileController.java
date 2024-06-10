package com.example.sopra.controller;

import com.example.sopra.entity.User;
import com.example.sopra.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/view")
    public String viewProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "profile/view";
    }

    @GetMapping("/edit")
    public String editProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute("user") User updatedUser, @RequestParam("profileImage") MultipartFile profileImage) throws IOException {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        userService.updateUserProfile(currentUser, updatedUser, profileImage);
        return "redirect:/profile/view";
    }
}
