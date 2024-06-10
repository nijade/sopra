package com.example.sopra.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;


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

    //private String photo;

    private Integer age;

    private String gender;

    private String profileDescription;


    public User() {
        // empty constructor for Hibernate
    }

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

    // TODO: Foto als Bilddatei darstellen
    /*public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }*/

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}