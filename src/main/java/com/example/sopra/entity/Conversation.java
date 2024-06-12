package com.example.sopra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Conversation {
    @jakarta.persistence.Id
    @GeneratedValue
    private Integer id;
    private Integer plantId;
    private Integer buyerId;
    @OneToMany
    private List <Message> messageList;




    public Conversation() {
    }


    public Integer getConversationId() {
        return id;
    }

    public void setPlantId(Integer advertId) {
        this.plantId = advertId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }


    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
