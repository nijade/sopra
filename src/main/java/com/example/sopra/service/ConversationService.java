package com.example.sopra.service;

import com.example.sopra.entity.Conversation;
import com.example.sopra.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation startConversation(Long plantId, Long buyerId, Long sellerId) {
        Conversation conversation = new Conversation();
        conversation.setPlantId(plantId);
        conversation.setBuyerId(buyerId);
        conversation.setSellerId(sellerId);
        conversation.setActive(true);
        return conversationRepository.save(conversation);
    }

    public void endConversation(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow();
        conversation.setActive(false);
        conversationRepository.save(conversation);
    }
}
