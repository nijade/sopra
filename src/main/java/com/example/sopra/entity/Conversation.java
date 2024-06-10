package com.example.sopra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class Conversation {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long advertId;
    private Long sellerId;
    private Long buyerId;


    public Conversation() {
    }

    public void setConversationId(Long conversationId) {
        this.id = conversationId;
    }

    public Long getConversationId() {
        return id;
    }


    public Long getAdvertId() {
        return advertId;
    }


    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
    }


    public Long getSellerId() {
        return sellerId;
    }


    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }


    public Long getBuyerId() {
        return buyerId;
    }


    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
