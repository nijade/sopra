package com.example.sopra.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue
    private Integer plantID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User seller;

    private String title;
    private Double price;

    private Integer height;

    private Boolean hasPlanter;

    private Double potCircumference;

    private Double plantCircumference;

    @Lob
    private String description;

    @ElementCollection
    private List<String> photos;

    @ElementCollection
    private List<String> tags;

    @ManyToOne
    private User buyerFinal;

    @ManyToMany(mappedBy = "faves")
    private List<User> favoritedBy = new ArrayList<>();

    public Plant(){
        // empty constructor for Hibernate
    }

    /**
     * Die nachfolgenden Methoden sind die Getter und Setter der Plant Attribute.
     */

    public Integer getPlantID() {
        return plantID;
    }

    public void setPlantID(Integer plantID) {
        this.plantID = plantID;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User user) {
        this.seller = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getHasPlanter() {
        return hasPlanter;
    }

    public void setHasPlanter(Boolean hasPlanter) {
        this.hasPlanter = hasPlanter;
    }

    public Double getPotCircumference() {
        return potCircumference;
    }

    public void setPotCircumference(Double potCircumference) {
        this.potCircumference = potCircumference;
    }

    public Double getPlantCircumference() {
        return plantCircumference;
    }

    public void setPlantCircumference(Double plantCircumference) {
        this.plantCircumference = plantCircumference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTagsAsString(){
        return String.join(", ", tags);
    }

    public User getBuyerFinal() {
        return buyerFinal;
    }

    public void setBuyerFinal(User buyerFinal) {
        this.buyerFinal = buyerFinal;
    }

    public List<User> getFavoritedBy() {
        return favoritedBy;
    }

    public void setFavoritedBy(List<User> favoritedBy) {
        this.favoritedBy = favoritedBy;
    }
}
