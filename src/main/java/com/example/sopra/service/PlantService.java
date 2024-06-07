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
            return "errorCreation";
        }
        try {
            User user = userService.getCurrentUser();
            Plant plant = new Plant();
            setAllPlantValues(plant, title, photos, height, price, hasPlanter, description, potCircumference, plantCircumference, tags, user);

            plantRepository.save(plant);
            return "successfulCreation";
        } catch (Exception e) {
            return "errorCreation";
        }
    }

    public Plant findPlantByID(int plantID){
        return plantRepository.findByPlantID(plantID);
    }

    public void deletePlant(Plant plant){
        plantRepository.delete(plant);
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
            model.addAttribute("You must select at least one photo");
            return "errorCreation";
        }
        try {
            Plant plant = findPlantByID(id);
            deletePlant(plant);
            User user = userService.getCurrentUser();
            if(user.getUserId() != plant.getUser().getUserId()){
                return "errorCreation";
            }
            setAllPlantValues(plant, title, photos, height, price, hasPlanter, description, potCircumference, plantCircumference, tags, user);
            savePlant(plant);

            return "successfulCreation";
        } catch (Exception e) {
            return "errorCreation";
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

}