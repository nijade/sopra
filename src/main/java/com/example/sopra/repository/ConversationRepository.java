package com.example.sopra.repository;

import com.example.sopra.entity.Conversation;
import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    public Conversation findConversationById(Integer id);

    public List<Conversation> findAllByBuyerUserId(Integer id);

    public List<Conversation> findAllByPlantSellerUserId(Integer id);

    public Conversation findConversationByPlantAndBuyer(Plant plant, User buyer);
}
