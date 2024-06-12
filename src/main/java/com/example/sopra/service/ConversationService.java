package com.example.sopra.service;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Message;
import com.example.sopra.repository.ConversationRepository;
import com.example.sopra.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageRepository messageRepository;

    public Conversation startConversation(Integer plantId, Integer buyerId) {
        Conversation conversation = new Conversation();
        conversation.setPlantId(plantId);
        conversation.setMessageList(new ArrayList<>());
        conversation.setBuyerId(buyerId);
        return conversationRepository.save(conversation);
    }

    public Message sendMessage(Integer senderId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }


    public Conversation addMessageToConversation(Integer conversationId, Message message){
        Conversation conversation = conversationRepository.findConversationById(conversationId);
        conversation.getMessageList().add(message);
        return conversationRepository.save(conversation);
    }



}