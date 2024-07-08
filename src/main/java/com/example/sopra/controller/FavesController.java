package com.example.sopra.controller;

import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FavesController {

    @Autowired
    private UserService userService;

    /**
     * Listet alle Pflanzen aus der Merkliste des aktuellen Benutzers auf.
     *
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Ansicht für die eigeneMerkliste von Pflanzen
     */
    @GetMapping("/myFaves")
    public String getUserFaves(Model model) {
        try {
            User currentUser = userService.getCurrentUser();
            model.addAttribute("currentUser", currentUser);
            List<Plant> favePlants = userService.getFavoritePlants(currentUser.getUserId());
            model.addAttribute("faves", favePlants);
            return "myFaves";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Es ist ein Fehler beim Finden der Pflanzen in der Merkliste aufgetreten!");
            return "errorCustom";
        }
    }

    /**
     * Nimmt die Anfrage zur Aufnahme einer Pflanze in die Merkliste entgegen und leitet zurück zur Pflanzen-Detailansicht.
     *
     * @param plantId Die id der Pflanze, die zur Merkliste hinzugefügt werden soll
     * @return die html-Seite der Detailansicht der hinzugefügten Pflanze
     */
    @PostMapping("/addToFaves/{id}")
    public String addToFaves(@PathVariable("id") Integer plantId) {
        User currentUser = userService.getCurrentUser();
        userService.addToFavorites(currentUser.getUserId(), plantId);
        return "redirect:/plants/" + plantId;
    }

    /**
     * Nimmt die Anfrage zum Entfernen einer Pflanze von der Merkliste entgegen und leitet zurück zur Pflanzen-Detailansicht.
     *
     * @param plantId Die id der Pflanze, die von der Merkliste entfernt werden soll
     * @return die html-Seite der Detailansicht der entfernten Pflanze
     */
    @PostMapping("/removeFromFaves/{id}")
    public String removeFromFaves(@PathVariable("id") Integer plantId) {
        User currentUser = userService.getCurrentUser();
        userService.removeFromFavorites(currentUser.getUserId(), plantId);
        return "redirect:/plants/" + plantId;
    }
}
