package com.example.sopra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CareInstruction {

    @Id
    private String tagTitle;

    private String water;

    private String fertilize;

    private String location;

    public CareInstruction(){
    }


    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getFertilize() {
        return fertilize;
    }

    public void setFertilize(String fertilize) {
        this.fertilize = fertilize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
