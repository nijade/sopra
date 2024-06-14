package com.example.sopra.repository;

import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    List<Plant> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT p FROM Plant p WHERE p.plantID = :plantID")
    Plant findByPlantID(@Param("plantID") Integer plantID);

    @Query("SELECT p FROM Plant p")
    List<Plant> findAll();


    @Query("SELECT p FROM Plant p WHERE p.seller = :currentUser")
    List<Plant> findPlantsByUser(@Param("currentUser") User user);


}