package com.example.sopra.controller;

import com.example.sopra.entity.Message;
import com.example.sopra.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    // Endpoint to send a message
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestParam Long conversationId, @RequestParam Long senderId, @RequestParam String messageText) {
        MessageData messageData = new MessageData(conversationId, senderId, messageText);
        Message message = messageService.sendMessage(conversationId, senderId, messageText);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Inner class to encapsulate message data
    private static class MessageData {
        private final Long conversationId;
        private final Long senderId;
        private final String messageText;

        public MessageData(Long conversationId, Long senderId, String messageText) {
            this.conversationId = conversationId;
            this.senderId = senderId;
            this.messageText = messageText;
        }

        public Long getConversationId() {
            return conversationId;
        }

        public Long getSenderId() {
            return senderId;
        }

        public String getMessageText() {
            return messageText;
        }
    }
}

