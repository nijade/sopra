package com.example.sopra.controller;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Message;
import com.example.sopra.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/conversations/start")
    public ResponseEntity<?> startConversation(@RequestParam Long advertId, @RequestParam Long sellerId, @RequestParam Long buyerId) {
        Conversation conversation = chatService.startConversation(advertId, sellerId, buyerId);
        return ResponseEntity.ok(conversation);
    }

    @PostMapping("/messages/send")
    public ResponseEntity<?> sendMessage(@RequestParam Long conversationId, @RequestParam Long senderId, @RequestParam String content) {
        Message message = chatService.sendMessage(conversationId, senderId, content);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/conversation")
    public String showChatPage() {
        return "conversation";
    }
}
