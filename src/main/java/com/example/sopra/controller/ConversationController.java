package com.example.sopra.controller;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Message;
import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import com.example.sopra.service.ConversationService;
import com.example.sopra.service.PlantService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Conversation-Klasse zur Festlegung der Conversation-Funktionalität und
 * Weiterleitung zwischen Frontend und Backend.
 */
@Controller
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    private final UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    public ConversationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Zeigt eine spezifische Konversation zugehörig zum User und überprüft, das User keine
     * doppelten Konversationen starten kann
     *
     * @param plantId
     * @param model
     * @return
     */
    @GetMapping("/conversation")
    public String showChatPage(@RequestParam("plantId") Integer plantId, Model model) {
        User currentUser = userService.getCurrentUser();
        Plant plant = plantService.findPlantByID(plantId);
        Conversation alreadyExistingConversation = conversationService.getConversationByPlantAndBuyer(plant, currentUser);
        if(alreadyExistingConversation == null){
            alreadyExistingConversation = conversationService.startConversation(plantId, currentUser.getUserId());
        }
        model.addAttribute("specificConversation", alreadyExistingConversation);
        return "conversation";
    }

    /**
     * Zeigt eine spezifische Konversation
     *
     * @param conversationId
     * @param model
     * @return
     */
    @GetMapping("/showConversation")
    public String showExistingChatPage(@RequestParam("conversationId") Integer conversationId, Model model) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        model.addAttribute("specificConversation", conversation);
        model.addAttribute("currentUsername", userService.getCurrentUser().getUsername());
        return "conversation";
    }

    /**
     * verlinkt bei Beendigung einer Konversation auf die Home-Seite
     *
     * @return
     */
    @GetMapping("/endConversation")
    public String endConversationLinkToHome() {
        return "redirect:/";
    }

    /**
     * Zeigt alle zum eingeloggten User zugehörigen Konversationen an
     *
     * @param model
     * @return
     */
    @GetMapping("/ownConversations")
    public String showOwnConversations(Model model) {
        User currentUser = userService.getCurrentUser();
        List<Conversation> conversationsOfUser = conversationService.getAllConversationsOfUser(currentUser);

        // Retrieve the last message for each conversation
        Map<Integer, Message> lastMessages = new HashMap<>();
        for (Conversation conversation : conversationsOfUser) {
            Message lastMessage = conversationService.getLastMessage(conversation.getConversationId());
            lastMessages.put(conversation.getConversationId(), lastMessage);
        }

        model.addAttribute("conversations", conversationsOfUser);
        model.addAttribute("lastMessages", lastMessages);
        return "ownConversations";
    }

    /**
     * Zeigt die geschriebenen Messages an, deren Logik im @ChatService verarbeitet wird
     *
     * @param messageInput
     * @param conversationId
     * @param model
     * @return
     */
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("messageInput") String messageInput,
                              @RequestParam("conversationId") Integer conversationId, Model model) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        boolean messageSent = false;
        if (messageInput != null && messageInput.length() > 0 && messageInput.length() < 250) {
            User currentUser = userService.getCurrentUser();
            Message message = conversationService.sendMessage(currentUser.getUserId(), messageInput);
            conversation = conversationService.addMessageToConversation(conversationId, message);
            messageSent = true;

            userService.addXp(userService.getCurrentUser(), 5);
        }
        model.addAttribute("specificConversation", conversation);
        model.addAttribute("messageSent", messageSent);
        model.addAttribute("currentUsername", userService.getCurrentUser().getUsername());
        return "conversation";
    }
}
