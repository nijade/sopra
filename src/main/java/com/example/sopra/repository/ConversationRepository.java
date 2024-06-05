package com.example.sopra.repository;

import com.example.sopra.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByBuyerId(Long buyerId);
    List<Conversation> findBySellerId(Long sellerId);
}
