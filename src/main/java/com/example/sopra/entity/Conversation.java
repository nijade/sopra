package com.example.sopra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * Klasse zur Definiton von Konversationen
 *
 */
@Entity
public class Conversation {
    @jakarta.persistence.Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Plant plant;
    @ManyToOne
    private User buyer;
    @OneToMany
    private List <Message> messageList;


    public Conversation() {
    }

    /**
     * Die nachfolgenden Methoden sind die Getter und Setter der Conversation Attribute.
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Integer getConversationId() {
        return id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
