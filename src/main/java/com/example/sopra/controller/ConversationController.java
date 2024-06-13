package com.example.sopra.controller;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Message;
import com.example.sopra.entity.User;
import com.example.sopra.service.ConversationService;
import com.example.sopra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;



    @GetMapping("/conversation")
    public String showChatPage(@RequestParam("plantId") Integer plantId, Model model) {
        User currentUser = userService.getCurrentUser();
        Conversation conversation = conversationService.startConversation(plantId, currentUser.getUserId());
        model.addAttribute("specificConversation", conversation);
        return "conversation";
    }

    @GetMapping("/showConversation")
    public String showExistingChatPage(@RequestParam("conversationId") Integer conversationId, Model model) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        model.addAttribute("specificConversation", conversation);
        return "conversation";
    }

    @GetMapping("/endConversation")
    public String endConversationLinkToHome() {
        return "home";
    }

    @GetMapping("/ownConversations")
    public String showOwnConversations(Model model) {
        User currentUser = userService.getCurrentUser();
        List<Conversation> conservationsOfUser = conversationService.getAllConversationsOfUser(currentUser);
        model.addAttribute("conversations", conservationsOfUser);
        return "ownConversations";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("messageInput") String messageInput,
                              @RequestParam("conversationId") Integer conversationId, Model model) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        if (messageInput != null && messageInput.length()>0 && messageInput.length()<250) {
            User currentUser = userService.getCurrentUser();
            Message message = conversationService.sendMessage(currentUser.getUserId(), messageInput);
            conversation = conversationService.addMessageToConversation(conversationId, message);
        }
        model.addAttribute("specificConversation", conversation);
        return "conversation";
    }

}
