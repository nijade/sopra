package com.example.sopra.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

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
        if (level >= 1 && level < 5) return "Grünohr";
        if (level >= 5 && level < 10) return "Blattknospe";
        if (level >= 10 && level < 15) return "Gartenzauberer";
        if (level >= 15 && level < 20) return "Pflanzenschützer";
        if (level >= 20 && level < 25) return "Floristenlehrling";
        if (level >= 25 && level < 30) return "Gartenpfleger";
        if (level >= 30 && level < 35) return "Botanikforscher";
        if (level >= 35 && level < 40) return "Pflanzenheiler";
        if (level >= 40 && level < 45) return "Blütenflüsterer";
        if (level >= 45 && level < 50) return "Gartenseher";
        if (level >= 50 && level < 55) return "Pflanzenalchemist";
        if (level >= 55 && level < 60) return "Wurzelspezialist";
        if (level >= 60 && level < 65) return "Gartenveteran";
        if (level >= 65 && level < 70) return "Pflanzenguru";
        if (level >= 70 && level < 75) return "Gartenmeister";
        if (level >= 75 && level < 80) return "Pflanzenmagier";
        if (level >= 80 && level < 85) return "Rankenritter";
        if (level >= 85 && level < 90) return "Knospenkaiser";
        if (level >= 90 && level < 95) return "Blütenkönig";
        if (level >= 95 && level < 100) return "Pflanzengroßmeister";
        if(level >= 100) return "Pflanzenbaron";
        return "Unbekannter Titel";
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
}