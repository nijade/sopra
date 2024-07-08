package com.example.sopra.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "app_user")
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Integer userId;

    private String username;

    private String password;

    private boolean enabled = true;

    private String name;

    private String email;

    private Integer age;

    private Gender gender;

    private String profileDescription;

    private String profileImage = "default.jpg";

    private int buys = 0;

    public int sales = 0;

    public int xp = 0;

    public int level = 1;

    public int xpToNextLevel = 50;

    @ElementCollection
    private List<Integer> reviews;

    @ManyToMany
    @JoinTable(
            name = "faves",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plant_id")
    )
    private List<Plant> faves = new ArrayList<>(); //List filled with plantIDs'


    public User() {
        // empty constructor for Hibernate
    }

    public void addXp(Integer xp){
        this.xp += xp;
        if(xp > xpToNextLevel){
            xp = xp - xpToNextLevel;
            level++;
            xpToNextLevel = calculateXpToNextLevel();
        }
    }

    // Method to get title based on level
    public String getTitle() {
        if (level >= 1 && level < 5) return "greenhorn";
        if (level >= 5 && level < 10) return "leafbud";
        if (level >= 10 && level < 15) return "gardensorcerer";
        if (level >= 15 && level < 20) return "plantprotector";
        if (level >= 20 && level < 25) return "floristapprentice";
        if (level >= 25 && level < 30) return "gardencaretaker";
        if (level >= 30 && level < 35) return "botanyresearcher";
        if (level >= 35 && level < 40) return "planthealer";
        if (level >= 40 && level < 45) return "blossomwhisperer";
        if (level >= 45 && level < 50) return "gardenseer";
        if (level >= 50 && level < 55) return "plantalchemist";
        if (level >= 55 && level < 60) return "rootspecialist";
        if (level >= 60 && level < 65) return "gardenveteran";
        if (level >= 65 && level < 70) return "plantguru";
        if (level >= 70 && level < 75) return "gardenmaster";
        if (level >= 75 && level < 80) return "plantmagician";
        if (level >= 80 && level < 85) return "vineknight";
        if (level >= 85 && level < 90) return "budemperor";
        if (level >= 90 && level < 95) return "blossomking";
        if (level >= 95 && level < 100) return "plantgrandmaster";
        if(level >= 100) return "plantbaron";
        return "unknowntitle";
    }


    public int calculateXpToNextLevel(){
        return 100*level;
    }

    /**
     * Die nachfolgenden Methoden sind die Getter und Setter der User Attribute.
     */

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getBuys() { return buys;}

    public void setBuys(int buys) { this.buys = buys;}

    public int getSales() { return sales;}

    public void setSales(int sells) { this.sales = sells;}

    public int getXp() { return xp;}

    public void setXp(int xp) { this.xp = xp;}

    public int getLevel() { return level;}

    public void setLevel(int level) { this.level = level;}

    public List<Plant> getFaves() {
        return faves;
    }

    public void setFaves(List<Plant> faves) {
        this.faves = faves;
    }

    public void addToFavorites(Plant plant) {
        this.faves.add(plant);
        plant.getFavoritedBy().add(this);
    }

    public void removeFromFavorites(Plant plant) {
        this.faves.remove(plant);
        plant.getFavoritedBy().remove(this);
    }

    public List<Plant> getFavoritePlants() {
        return faves;
    }

    public void setFavoritePlants(List<Plant> favoritePlants) {
        this.faves = favoritePlants;
    }
}