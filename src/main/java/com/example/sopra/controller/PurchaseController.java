package com.example.sopra.controller;

import com.example.sopra.entity.CareInstruction;
import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import com.example.sopra.repository.PlantRepository;
import com.example.sopra.service.CareInstructionService;
import com.example.sopra.service.ConversationService;
import com.example.sopra.service.PlantService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private CareInstructionService careInstructionService;

    @Autowired
    PlantRepository plantRepository;

    /**
     * zeigt die Seite an auf welcher die eigenen Köufe an Inseraten angezeigt werden
     *
     * @param model
     * @return
     */
    @GetMapping("/ownPurchases")
    public String showPurchasesPage(Model model){
        User currentUser = userService.getCurrentUser();
        List<Plant> ownPurchases = plantRepository.findAllByBuyerFinal(currentUser);
        model.addAttribute("ownPlants", ownPurchases);
        return "ownPurchases";
    }

    /**
     * führt den Verkauf der Pflanze durch über die sell-Logik in @conversationService
     *
     * @param conversationId
     * @return
     */
    @GetMapping("/sold")
    public String sellPlantToUser(@RequestParam("conversationId") Integer conversationId){
        Conversation conversationToSell = conversationService.getConversationById(conversationId);
        conversationService.sellPlantBasedOnConversation(conversationToSell);
        return "redirect:/showConversation?conversationId=" + conversationToSell.getConversationId();
    }


    /**
     * zeigt die entsprechenden Pflegetipps an, zugehörig zur entsprechenden Pflanze des gekauften Inserats
     *
     * @param plantId
     * @param model
     * @return
     */
    @GetMapping("/careInstruction")
    public String readCareInstructions(@RequestParam("plantId") Integer plantId, Model model){
        Plant plant = plantService.findPlantByID(plantId);
        model.addAttribute("plant", plant);
        boolean careAvailable = false;
        if(plant.getTags() != null){
            if(!plant.getTags().isEmpty()){
                careAvailable = true;
            }
        }
        model.addAttribute("careAvailable",  careAvailable);
        if(careAvailable){
           CareInstruction careInstruction = careInstructionService.getCareInstructionForPlant(plant);
           model.addAttribute("careInstruction", careInstruction);
        }
        return "careInstruction";
    }
}
