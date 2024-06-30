package com.example.sopra.service;

import com.example.sopra.entity.CareInstruction;
import com.example.sopra.entity.Plant;
import com.example.sopra.repository.CareInstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareInstructionService {

    @Autowired
    private CareInstructionRepository careInstructionRepository;

    /**
     * entsprechenden Pflegetipp für Pflanze abspeichern
     *
     * @param careInstruction
     * @return
     */
    public CareInstruction save(CareInstruction careInstruction){
        return careInstructionRepository.save(careInstruction);
    }

    /**
     * entsprechenden Pflegetipp zur Pflanze abrufen
     *
     * @param plant
     * @return
     */
    public CareInstruction getCareInstructionForPlant(Plant plant){
        return careInstructionRepository.findCareInstructionByTagTitle(plant.getTags().get(0));
    }
}
