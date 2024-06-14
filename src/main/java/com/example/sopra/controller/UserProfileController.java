package com.example.sopra.controller;

import com.example.sopra.entity.User;
import com.example.sopra.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Der Controller für die Verlinkung der HTML-Seiten zu "profile", bzw. dem Userprofil.
 */
@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    /**
     * Nimmt die Anfrage zur Profilübersicht entgegen und gibt die entsprechende Seite zurück. Wenn der/die
     * Benutzer*in noch nicht angemeldet ist, wird diese*r an die login-Seite geleitet.
     * @param model model, das user übergibt
     * @return die zu erscheinende html-Seite
     */
    @GetMapping("/view")
    public String viewProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "profile/view";
    }

    /**
     * Nimmt die Anfrage zum Bearbeiten des Profils entgegen und gibt die entsprechende Seite zurück. Wenn der/die
     * Benutzer*in noch nicht angemeldet ist, wird diese*r an die login-Seite geleitet.
     * @param model model, das user übergibt
     * @return die zu erscheinende html-Seite
     */
    @GetMapping("/edit")
    public String editProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser);
        return "profile/edit";
    }

    /**
     * Nimmt die Anfrage zum Speichern der neuen Profildaten entgegen und gibt wieder die Profilansicht zurück. Wenn
     * der/die Benutzer*in noch nicht angemeldet ist, wird diese*r an die login-Seite geleitet.
     * @param updatedUser Nachstellung eines Nutzers mit den geänderten Attributen
     * @return die zu erscheinende html-Seite
     */
    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute("user") User updatedUser) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        userService.updateUserProfile(currentUser, updatedUser);
        return "redirect:/profile/view";
    }
}
