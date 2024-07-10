package com.example.sopra.controller;

import com.example.sopra.entity.User;
import com.example.sopra.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Der Controller für die Verlinkung der HTML-Seiten zu "profile", bzw. dem Userprofil.
 */
@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    /**
     * Nimmt die Anfrage "/view", zur Profilansicht, entgegen und gibt die entsprechende Seite zurück. Wenn der/die
     * Benutzer*in noch nicht angemeldet ist, wird diese*r an die login-Seite geleitet. Ansonsten wird das aktuelle
     * Benutzerobjekt; die übersetzte Geschlechtsbezeichnung und die Profilvollständigkeit dem Modell hinzugefügt und
     * die Profilansicht angezeigt.
     * @param model model, das user übergibt
     * @param locale aktuelle Lokalisierung, um die richtige Übersetzung zu laden
     * @return die zu erscheinende html-Seite, also die Profilansicht
     */
    @GetMapping("/view")
    public String viewProfile(Model model, Locale locale) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", currentUser);

        String genderKey = "profile." + currentUser.getGender().name().toLowerCase();
        String gender = messageSource.getMessage(genderKey, null, locale);
        model.addAttribute("translatedGender", gender);

        int profileCompletion = userService.calculateProfileCompletion(currentUser);
        model.addAttribute("profileCompletion", profileCompletion);

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
     * der/die Benutzer*in noch nicht angemeldet ist, wird diese*r an die login-Seite geleitet. In jedem Fall werden
     * die neuen Attribute, die öffentlich sichtbar sein sollen aktualisiert.
     *
     * @param updatedUser Nachstellung eines Nutzers mit den geänderten Attributen
     * @param visibleAttributes Jedes angekreuzte Feld, was sichtbar sein soll wird als Liste übergeben, egal ob neu oder nicht
     * @return die zu erscheinende html-Seite
     */
    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute("user") User updatedUser, @RequestParam(required=false) List<String> visibleAttributes) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        userService.updateUserProfile(currentUser, updatedUser);
        userService.updateVisibleAttributes(currentUser.getUsername(), visibleAttributes);

        return "redirect:/profile/view";
    }

    /**
     * Nimmt die Anfrage zum Öffnen eines öffentlichen Profils entgegen und gibt wieder die Profilansicht zu dem Profil,
     * das als Parameter übergeben wird zurück. Wenn der/die Benutzer*in noch nicht angemeldet ist, wird diese*r an die login-Seite geleitet.
     *
     * @param username Username des öffentlichen Profils, das aufgerufen werden soll
     * @param model model, das user übergibt
     * @return html-Seite zum öffentlichen Profil
     */
    @GetMapping("/public")
    public String publicProfile(@RequestParam String username, Model model, Locale locale) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);

        String genderKey = "profile." + user.getGender().name().toLowerCase();
        String gender = messageSource.getMessage(genderKey, null, locale);
        model.addAttribute("translatedGender", gender);

        return "profile/public";
    }
}
