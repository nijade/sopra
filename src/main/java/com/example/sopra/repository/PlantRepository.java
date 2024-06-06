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

    @Query("SELECT p FROM Plant p WHERE p.title LIKE %:title%")
    List<Plant> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT p FROM Plant p WHERE p.plantID = :plantID")
    Plant findByPlantID(@Param("plantID") Integer plantID);

}