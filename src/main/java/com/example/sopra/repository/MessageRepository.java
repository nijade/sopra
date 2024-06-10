package com.example.sopra.repository;

import com.example.sopra.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversation_Id(Long conversationId);
}