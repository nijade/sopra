package com.example.sopra.service;

import com.example.sopra.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.sopra.entity.Message;
import com.example.sopra.entity.Conversation;

import java.time.LocalDateTime;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Long conversationId, Long senderId, String messageText) {
        Message message = new Message();
        message.setConversation(new Conversation());
        message.getConversation().setId(conversationId);
        message.setSenderId(senderId);
        message.setMessageText(messageText);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
