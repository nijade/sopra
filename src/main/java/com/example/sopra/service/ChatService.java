package com.example.sopra.service;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Message;
import com.example.sopra.repository.ConversationRepository;
import com.example.sopra.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageRepository messageRepository;

    public Conversation startConversation(Long advertId, Long sellerId, Long buyerId) {
        Conversation conversation = new Conversation();
        conversation.setAdvertId(advertId);
        conversation.setSellerId(sellerId);
        conversation.setBuyerId(buyerId);
        return conversationRepository.save(conversation);
    }

    public Message sendMessage(Long conversationId, Long senderId, String content) {
        Message message = new Message();
        message.setConversation(conversationRepository.findById(conversationId).orElseThrow());
        message.setSenderId(senderId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public void endConversation(Long conversationId) {
        if (conversationRepository.existsById(conversationId)) {
            conversationRepository.deleteById(conversationId);
        } else {
            throw new IllegalStateException("Konversation mit ID " + conversationId + " nicht gefunden.");
        }
    }
}