package com.example.sopra.controller;

import com.example.sopra.entity.Conversation;
import com.example.sopra.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    @GetMapping("/conversation")
    public String showConversation() {
        return "conversation";
    }

    @PostMapping("/start")
    public Conversation startConversation(@RequestParam Long plantId, @RequestParam Long buyerId, @RequestParam Long sellerId) {
        return conversationService.startConversation(plantId, buyerId, sellerId);
    }

    @PostMapping("/end/{conversationId}")
    public void endConversation(@PathVariable Long conversationId) {
        conversationService.endConversation(conversationId);
    }
}
