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

    public Plant findPlantByID(int plantID){
        return plantRepository.findByPlantID(plantID);
    }

    public String deletePlant(int id, Model model) {
        Plant plant = findPlantByID(id);
        if (plant != null && userService.getCurrentUser().getUserId().equals(plant.getUser().getUserId())) {
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



    public Plant savePlant(Plant plant){
        return plantRepository.save(plant);
    }

    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

    //Method for searching for plants by their title
    public List<Plant> searchPlantsByTitleContainingIgnoreCase(String title) {
        return plantRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Plant> findPlantsByUser(){
        return plantRepository.findPlantsByUser(userService.getCurrentUser());
    }

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
            if(!(user.getUserId().equals(plant.getUser().getUserId()))){
                model.addAttribute("errorMessage", "Sie konnten nicht als Anbieter authentifiziert werden!");
                return "errorCustom";
            }
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
        plant.setUser(user);
    }

    public String getPlantPage(int id, Model model) {
        Plant plant = findPlantByID(id);
        if(plant == null){
            model.addAttribute("errorMessage","Dieses Inserat existiert leider nicht");
            return "errorCustom";
        }
        model.addAttribute("plant", plant);
        List<String> photos =imageService.getImageNames();
        model.addAttribute("photos", photos);

        if(userService.getCurrentUser().getUserId().equals(plant.getUser().getUserId())){
            return "advertAsOwner";
        } else{
            return "advert";
        }
    }
}