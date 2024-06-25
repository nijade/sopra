package com.example.sopra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * KLasse zur Definition von Messages
 */
@Entity
public class Message {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer messageId;

    private String content;
    @ManyToOne
    private User sender;

    private LocalDateTime timestamp;

    public Message() {
    }

    /**
     * Die nachfolgenden Methoden sind die Getter und Setter der Message Attribute.
     */

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }


    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}

