package com.example.sopra.controller;
import com.example.sopra.entity.Plant;
import com.example.sopra.service.ImageService;
import com.example.sopra.service.PlantService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
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
    public String searchResults(@RequestParam String title,String category, Model model) {
        List<Plant> plants;
        String chosenSorting = "Am relevantesten";
        String alternativeSortingOne = "Günstigste Zuerst";
        String alternativeSortingTwo = "Teuerste Zuerst";
        if(category.equals("Alle Kategorien")){
            plants = plantService.searchPlantsByTitleContainingIgnoreCase(title);
        }else{
            plants = plantService.searchPlantsByTitleContainingIgnoreCaseSpecificCategory(title, category);
        }
        model.addAttribute("chosenSorting", chosenSorting);
        model.addAttribute("alternativeSortingOne",alternativeSortingOne);
        model.addAttribute("alternativeSortingTwo",alternativeSortingTwo);
        model.addAttribute("plants", plants);
        model.addAttribute("category", category);
        model.addAttribute("searchTerm", title);
        return "searchPlants";
    }

    // Methode zum Suchen nach Pflanzen mit Anwendung von Filtern
    @GetMapping("/searchPlantsAdditionalFilters")
    public String searchResultsAdditionalFilters(@RequestParam String title,
                                                 @RequestParam String category,
                                                 @RequestParam("selectedOption") String selectedOption,
                                                 @RequestParam(required = false) String priceMin,
                                                 @RequestParam(required = false) String priceMax,
                                                 @RequestParam(required = false) String heightMin,
                                                 @RequestParam(required = false) String heightMax,
                                                 @RequestParam(required = false) String circumferenceMin,
                                                 @RequestParam(required = false) String circumferenceMax,
                                                 Model model) {

        //Überprüfen der Maximalwerte um null zu vermeiden
        List<String> maxValues = new ArrayList<>();
        maxValues.add(priceMax);
        maxValues.add(heightMax);
        maxValues.add(circumferenceMax);
        for(int i= 0 ; i< maxValues.size(); i++){
            if(maxValues.get(i) == ""){
                maxValues.set(i, "2147483647");
            }
        }
        priceMax = maxValues.get(0);
        heightMax = maxValues.get(1);
        circumferenceMax = maxValues.get(2);

        //parsing RequestParameters to match QuerySpecifications
        Double priceMinQueryReady = Double.parseDouble(priceMin);
        Double priceMaxQueryReady = Double.parseDouble(priceMax);
        Integer heightMinQueryReady = Integer.parseInt(heightMin);
        Integer heightMaxQueryReady = Integer.parseInt(heightMax);
        Double  circumferenceMinQueryReady = Double.parseDouble(circumferenceMin);
        Double circumferenceMaxQueryReady = Double.parseDouble(circumferenceMax);

        // Verarbeitung der Parameter und Rückgabe der Suchergebnisse
        List<Plant> plants;
        String chosenSorting = "Am relevantesten";
        String alternativeSortingOne = "Günstigste Zuerst";
        String alternativeSortingTwo = "Teuerste Zuerst";


        if(category.equals("Alle Kategorien")){
            //Alle Kategorien
            if(selectedOption.equals("noSorting")){
                //Pflanzen ohne Sortierung - Alle Kategorien
                plants = plantService.searchPlantsByTitleContainingIgnoreCaseAdditionalFilters(title, priceMinQueryReady, priceMaxQueryReady, heightMinQueryReady, heightMaxQueryReady, circumferenceMinQueryReady, circumferenceMaxQueryReady);
            }else if(selectedOption.equals("priceASC")){
                //Pflanzen nach aufsteigendem Preis sortieren - Alle Kategorien
                plants = plantService.searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersPriceASC(title, priceMinQueryReady, priceMaxQueryReady, heightMinQueryReady, heightMaxQueryReady, circumferenceMinQueryReady, circumferenceMaxQueryReady);
            }else{
                //Pflanzen nach absteigendem Preis sortieren - Alle Kategorien
                plants = plantService.searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersPriceDSC(title, priceMinQueryReady, priceMaxQueryReady, heightMinQueryReady, heightMaxQueryReady, circumferenceMinQueryReady, circumferenceMaxQueryReady);
            }
            // alle anderen Kategorien
        } else {
            if(selectedOption.equals("noSorting")){
                //Pflanzen ohne Sortierung - Spezifische Kategorie
                plants = plantService.searchPlantsByTitleContainingIgnoreCaseSpecificCategoryAdditionalFiltersSpecificCategory(title, category, priceMinQueryReady, priceMaxQueryReady, heightMinQueryReady, heightMaxQueryReady, circumferenceMinQueryReady, circumferenceMaxQueryReady);
            }else if(selectedOption.equals("priceASC")){
                //Pflanzen nach aufsteigendem Preis sortieren(günstigste zuerst) - Spezifische Kategorie
                plants = plantService.searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersSpecificCategoryPriceASC(title, category, priceMinQueryReady, priceMaxQueryReady, heightMinQueryReady, heightMaxQueryReady, circumferenceMinQueryReady, circumferenceMaxQueryReady);
            }else{
                //Pflanzen nach absteigendem Preis sortieren (tuerste zuerst) - Spezifische Kategorie
                plants = plantService.searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersSpecificCategoryPriceDSC(title, category, priceMinQueryReady, priceMaxQueryReady, heightMinQueryReady, heightMaxQueryReady, circumferenceMinQueryReady, circumferenceMaxQueryReady);
            }
        }

        model.addAttribute("chosenSorting", chosenSorting);
        model.addAttribute("alternativeSortingOne",alternativeSortingOne);
        model.addAttribute("alternativeSortingTwo",alternativeSortingTwo);
        model.addAttribute("plants", plants);
        model.addAttribute("category", category);
        model.addAttribute("searchTerm", title);
        model.addAttribute("selectOption", selectedOption);

        //Attribute des Filter-Forms
        model.addAttribute("priceMinimum", priceMin);
        model.addAttribute("priceMaximum", priceMax);
        model.addAttribute("heightMinimum", heightMin);
        model.addAttribute("heightMaximum", heightMax);
        model.addAttribute("circumferenceMaximum", circumferenceMax);
        model.addAttribute("circumferenceMinimum", circumferenceMin);

        return "searchPlants"; // Die View, in der die Suchergebnisse angezeigt werden
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
