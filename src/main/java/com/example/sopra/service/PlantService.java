package com.example.sopra.service;

import com.example.sopra.entity.Plant;
import com.example.sopra.repository.PlantRepository;
import com.example.sopra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PlantService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlantRepository plantRepository;

    public Plant savePlant(Plant plant){
        return plantRepository.save(plant);
    }

    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

    //Method for searching for plants by their title
    public List<Plant> searchPlantsByTitle(String title) {
        return plantRepository.findByTitleContaining(title);
    }
}