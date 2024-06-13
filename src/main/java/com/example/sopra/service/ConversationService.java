package com.example.sopra.service;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Message;
import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import com.example.sopra.repository.ConversationRepository;
import com.example.sopra.repository.MessageRepository;
import com.example.sopra.repository.PlantRepository;
import com.example.sopra.repository.UserRepository;
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
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private UserRepository userRepository;


    public Conversation startConversation(Integer plantId, Integer buyerId) {
        Conversation conversation = new Conversation();
        Plant plant = plantRepository.findByPlantID(plantId);
        conversation.setPlant(plant);
        conversation.setMessageList(new ArrayList<>());
        User buyer = userRepository.findByUserId(buyerId);
        conversation.setBuyer(buyer);
        return conversationRepository.save(conversation);
    }

    public Message sendMessage(Integer senderId, String content) {
        Message message = new Message();
        User sender = userRepository.findByUserId(senderId);
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }


    public Conversation addMessageToConversation(Integer conversationId, Message message){
        Conversation conversation = conversationRepository.findConversationById(conversationId);
        conversation.getMessageList().add(message);
        return conversationRepository.save(conversation);
    }

    public List<Conversation> getAllConversationsOfUser(User currentUser){
        List<Conversation> conversations = new ArrayList<>();
        conversations.addAll(conversationRepository.findAllByBuyerUserId(currentUser.getUserId()));
        conversations.addAll(conversationRepository.findAllByPlantSellerUserId(currentUser.getUserId()));
        return conversations;
    }


    public Conversation getConversationById(Integer conversationId){
        return conversationRepository.findConversationById(conversationId);
    }

    public Conversation getConversationByPlantAndBuyer(Plant plant, User user){
       return conversationRepository.findConversationByPlantAndBuyer(plant, user);
    }

}