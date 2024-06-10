package com.example.sopra.repository;

import com.example.sopra.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByAdvertIdAndBuyerId(Long advertId, Long buyerId);
}
