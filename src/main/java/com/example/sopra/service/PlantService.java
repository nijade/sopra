package com.example.sopra.service;

import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import com.example.sopra.repository.PlantRepository;
import com.example.sopra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;


/**
 * Service-Klasse für die Verwaltung von Pflanzeninseraten.
 */
@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PlantService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;


    /**
     * Erstellt ein neues Pflanzeninserat.
     *
     * @param title             der Titel des Inserats
     * @param photos            die Liste der Fotos
     * @param height            die Höhe der Pflanze
     * @param price             der Preis der Pflanze
     * @param hasPlanter        ob die Pflanze einen Pflanztopf hat
     * @param description       die Beschreibung des Inserats
     * @param potCircumference  der Umfang des Pflanztopfs
     * @param plantCircumference der Umfang der Pflanze
     * @param tags              die Tags des Inserats
     * @param model             das Model-Objekt für die Übergabe an die View
     * @return String fürErfolgsmeldung/Fehlermeldung mit Weiterleitung zur Homepage
     */
    public String createPlant(String title,
                              List<String> photos,
                              Integer height,
                              Double price,
                              Boolean hasPlanter,
                              String description,
                              Double potCircumference,
                              Double plantCircumference,
                              String tags,
                              Model model) {
        if(photos == null || photos.isEmpty()){
            model.addAttribute("errorMessage", "Bitte wählen Sie mindestens ein Bild aus!");
            return "errorCustom";
        }
        try {
            User user = userService.getCurrentUser();
            Plant plant = new Plant();
            setAllPlantValues(plant, title, photos, height, price, hasPlanter, description, potCircumference, plantCircumference, tags, user);

            plantRepository.save(plant);
            model.addAttribute("successMessage", "Ihr Inserat wurde erfolgreich erstellt!");
            return "success";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Es ist ein Fehler aufgetreten!");
            return "errorCustom";
        }
    }

    /**
     * Findet eine Pflanze anhand der ID.
     *
     * @param plantID die ID der Pflanze
     * @return Plant das Pflanzen-Objekt
     */
    public Plant findPlantByID(int plantID){
        return plantRepository.findByPlantID(plantID);
    }

    /**
     * Löscht ein Pflanzeninserat.
     *
     * @param id    die ID des Inserats
     * @param model das Model-Objekt für die Übergabe an die View
     * @return String die View-Name
     */
    public String deletePlant(int id, Model model) {
        Plant plant = findPlantByID(id);

        //Überprüfung ob Nutzer berechtigt ist, um die Pflanze zu löschen
        if (plant != null && userService.getCurrentUser().getUserId().equals(plant.getSeller().getUserId())) {
            try {
                plantRepository.deleteById(id);
                model.addAttribute("successMessage","Das Inserat wurde erfolgreich gelöscht!");
                return "success";
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Es ist ein Fehler aufgetreten!");
                return "errorCustom";
            }
        }
        model.addAttribute("errorMessage", "Das Inserat existiert nicht oder wurde nicht von Ihnen erstellt!");
        return "errorCustom";
    }


    /**
     * Speichert ein Pflanzeninserat.
     *
     * @param plant das Pflanzen-Objekt
     * @return Plant das gespeicherte Pflanzen-Objekt
     */
    public Plant savePlant(Plant plant){
        return plantRepository.save(plant);
    }

    /**
     * Findet alle Pflanzeninserate.
     *
     * @return List<Plant> die Liste der Pflanzeninserate
     */
    public List<Plant> findAllPlants() {
        return plantRepository.findAll();

    }

    /**
     * Sucht nach Pflanzen anhand des Titels (case-insensitive).
     *
     * @param title der Titel der Pflanze
     * @return List<Plant> die Liste der gefundenen Pflanzen
     */
    public List<Plant> searchPlantsByTitleContainingIgnoreCase(String title) {
        return plantRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Plant> searchPlantsByTitleContainingIgnoreCasePriceAscending(String title) {
        return plantRepository.findByTitleContainingIgnoreCasePriceAscending(title);
    }

    public List<Plant> searchPlantsByTitleContainingIgnoreCaseSpecificCategory(String title, String category) {
        return plantRepository.findByTitleContainingIgnoreCaseSpecificCategory(title, category);
    }

    public List<Plant> searchPlantsByTitleContainingIgnoreCasePriceAscendingSpecificCategory(String title, String category) {
        return plantRepository.findByTitleContainingIgnoreCasePriceAscendingSpecificCategory(title, category);
    }



    /**
     * Findet alle Pflanzeninserate eines Benutzers.
     *
     * @return List<Plant> die Liste der Pflanzeninserate des Benutzers
     */
    public List<Plant> findPlantsByUser(){
        return plantRepository.findPlantsByUser(userService.getCurrentUser());
    }

    /**
     * Aktualisiert ein bestehendes Pflanzeninserat.
     *
     * @param id                die ID des Inserats
     * @param title             der Titel des Inserats
     * @param photos            die Liste der Fotos
     * @param height            die Höhe der Pflanze
     * @param price             der Preis der Pflanze
     * @param hasPlanter        ob die Pflanze einen Pflanztopf hat
     * @param description       die Beschreibung des Inserats
     * @param potCircumference  der Umfang des Pflanztopfs
     * @param plantCircumference der Umfang der Pflanze
     * @param tags              die Tags des Inserats
     * @param model             das Model-Objekt für die Übergabe an die View
     * @return String für Erfolgsmeldung/Fehlermeldung mit Weiterleitung zur Homepage
     */
    public String updatePlant(int id,
                            String title,
                            List<String> photos,
                            Integer height,
                            Double price,
                            Boolean hasPlanter,
                            String description,
                            Double potCircumference,
                            Double plantCircumference,
                            String tags,
                            Model model) {
        if(photos == null || photos.isEmpty()){
            model.addAttribute("errorMessage", "Bitte wählen Sie mindestens ein Bild aus!");
            return "errorCustom";
        }
        try {
            Plant plant = findPlantByID(id);

            User user = userService.getCurrentUser();
            if(!(user.getUserId().equals(plant.getSeller().getUserId()))){
                model.addAttribute("errorMessage", "Sie konnten nicht als Anbieter authentifiziert werden!");
                return "errorCustom";
            }
            //Entfernen der Pflanze und erneutes Hinzufügen nach Änderung der Attributeum Aktualisierung zu garantieren
            deletePlant(id, model);
            setAllPlantValues(plant, title, photos, height, price, hasPlanter, description, potCircumference, plantCircumference, tags, user);
            savePlant(plant);

            model.addAttribute("successMessage", "Ihr Inserat wurde erfolgreich bearbeitet!");
            return "success";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Etwas ist schiefgelaufen!");
            return "errorCustom";
        }

    }

    /**
     * Hilfsmethode, um die Attribute des Inserats gebündelt zu ändern
     *
     * @param plant             das Pflanzen-Objekt
     * @param title             der Titel des Inserats
     * @param photos            die Liste der Fotos
     * @param height            die Höhe der Pflanze
     * @param price             der Preis der Pflanze
     * @param hasPlanter        ob die Pflanze einen Pflanztopf hat
     * @param description       die Beschreibung des Inserats
     * @param potCircumference  der Umfang des Pflanztopfs
     * @param plantCircumference der Umfang der Pflanze
     * @param tags              die Tags des Inserats
     * @param user              der Benutzer, der das Inserat erstellt
     */
    private void setAllPlantValues(Plant plant, String title, List<String> photos, Integer height, Double price, Boolean hasPlanter, String description, Double potCircumference, Double plantCircumference, String tags, User user) {
        plant.setTitle(title);
        plant.setPhotos(photos);
        plant.setHeight(height);
        plant.setPrice(price);
        plant.setHasPlanter(hasPlanter != null ? hasPlanter : false);
        plant.setDescription(description);
        plant.setPotCircumference(potCircumference);
        plant.setPlantCircumference(plantCircumference);
        plant.setTags(Arrays.asList(tags.split(",")));
        plant.setSeller(user);
    }

    /**
     * Ruft die Seite für ein Pflanzeninserat auf.
     *
     * @param id    die ID des Inserats
     * @param model das Model-Objekt für die Übergabe an die View
     * @return String für Seite des Inserats als Interessent/Besitzer
     */
    public String getPlantPage(int id, Model model) {
        Plant plant = findPlantByID(id);
        if(plant == null){
            model.addAttribute("errorMessage","Dieses Inserat existiert leider nicht");
            return "errorCustom";
        }
        model.addAttribute("plant", plant);
        List<String> photos =imageService.getImageNames();
        model.addAttribute("photos", photos);

        //Anzeige der Seite in Abhängikeit vom Ersteller der Seite
        if(userService.getCurrentUser().getUserId().equals(plant.getSeller().getUserId())){
            return "advertAsOwner";
        } else{
            return "advert";
        }
    }

}