package com.example.sopra.repository;

import com.example.sopra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
 public interface UserRepository extends JpaRepository<User, Integer> {

 }

