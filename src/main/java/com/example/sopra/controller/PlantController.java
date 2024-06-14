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

/**
 * Controller-Klasse zur Verwaltung von Pflanzenanzeigen.
 */
@Controller
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PlantController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private ImageService imageService;


    /**
     * Zeigt die Seite zum Erstellen einer neuen Pflanze an.
     *
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Ansicht für die Pflanzenerstellung
     */
    @GetMapping("/create")
    public String showCreate(Model model) {
        List<String> photos = imageService.getImageNames();
        model.addAttribute("photos", photos);
        return "create";
    }

    /**
     * Sucht nach Pflanzen mit einem bestimmten Titel.
     *
     * @param title der Titel der Pflanze
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Ansicht für die Suchergebnisse
     */
    @GetMapping("/searchPlants")
    public String searchResults(@RequestParam String title, Model model) {
        List<Plant> plants = plantService.searchPlantsByTitleContainingIgnoreCase(title);
        model.addAttribute("plants", plants);
        model.addAttribute("searchTerm", title);
        return "searchPlants";
    }

    /**
     * Listet alle Pflanzen des aktuellen Benutzers auf.
     *
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Ansicht für die eigenen Pflanzen
     */
    @GetMapping("/myplants")
    public String listMyPlants(Model model){
        List<Plant> plants = plantService.findPlantsByUser();
        model.addAttribute("plants", plants);
        return "myPlants";
    }

    /**
     * Erstellt eine neue Pflanzenanzeige.
     *
     * @param title             der Titel der Pflanze
     * @param photos            die Fotos der Pflanze
     * @param height            die Höhe der Pflanze
     * @param price             der Preis der Pflanze
     * @param hasPlanter        ob die Pflanze einen Pflanztopf hat
     * @param description       die Beschreibung der Pflanze
     * @param potCircumference  der Topfumfang
     * @param plantCircumference der Pflanzenumfang
     * @param tags              die Tags der Pflanze
     * @param model             das Modell, das der Ansicht hinzugefügt wird
     * @return Weiterleitung zu Erfolgs-/Fehlermeldung und letztlich der Homepage
     */
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

    /**
     * Zeigt das Bearbeitungsformular für eine bestehende Pflanzenanzeige an.
     *
     * @param id    die ID der Pflanze
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Ansicht für die Pflanzenbearbeitung
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Plant plant = plantService.findPlantByID(id);
        model.addAttribute("plant", plant);
        List<String> photos = imageService.getImageNames();
        model.addAttribute("photos", photos);
        return "editPlant";
    }

    /**
     * Aktualisiert eine bestehende Pflanzenanzeige.
     *
     * @param id                die ID der Pflanze
     * @param title             der Titel der Pflanze
     * @param photos            die Fotos der Pflanze
     * @param height            die Höhe der Pflanze
     * @param price             der Preis der Pflanze
     * @param hasPlanter        ob die Pflanze einen Pflanztopf hat
     * @param description       die Beschreibung der Pflanze
     * @param potCircumference  der Topfumfang
     * @param plantCircumference der Pflanzenumfang
     * @param tags              die Tags der Pflanze
     * @param model             das Modell, das der Ansicht hinzugefügt wird
     * @return Weiterleitung zu Erfolgs-/Fehlermeldung und letztlich der Homepage
     */
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

    /**
     * Zeigt die Detailseite einer Pflanze an.
     *
     * @param id    die ID der Pflanze
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Detailansicht der Pflanze
     */
    @GetMapping("/plants/{id}")
    public String showPlant(@PathVariable int id, Model model){
        return plantService.getPlantPage(id, model);
    }

    /**
     * Löscht eine Pflanzenanzeige.
     *
     * @param id    die ID der Pflanze
     * @param model das Modell, das der Ansicht hinzugefügt wird
     * @return der Name der Ansicht nach dem Löschen der Pflanze
     */
    @PostMapping("/delete/{id}")
    public String deletePlant(@PathVariable int id, Model model){
        return plantService.deletePlant(id, model);
    }
}
