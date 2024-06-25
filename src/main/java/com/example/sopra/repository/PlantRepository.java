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

    //Queries without specified category
    //Standard Query for finding Plants by searching for their title
    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    List<Plant> findByTitleContainingIgnoreCase(@Param("title") String title);

    //Standard Query for finding Plants by searching for their title and sorting them by Ascending Price
    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%')) ORDER BY p.price ASC")
    List<Plant> findByTitleContainingIgnoreCasePriceAscending(@Param("title") String title);

    //Specific category queries
    //Standard Query for finding Plants by searching for their title and Category
    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%')) AND :category MEMBER OF p.tags")
    List<Plant> findByTitleContainingIgnoreCaseSpecificCategory(@Param("title") String title, @Param("category") String category);

    //Standard Query for finding Plants by searching for their title and Category then sorting them by ascending Price
    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%')) AND :category MEMBER OF p.tags ORDER BY p.price ASC")
    List<Plant> findByTitleContainingIgnoreCasePriceAscendingSpecificCategory(@Param("title") String title, @Param("category") String category);




    @Query("SELECT p FROM Plant p WHERE p.plantID = :plantID")
    Plant findByPlantID(@Param("plantID") Integer plantID);

    @Query("SELECT p FROM Plant p")
    List<Plant> findAll();


    @Query("SELECT p FROM Plant p WHERE p.seller = :currentUser")
    List<Plant> findPlantsByUser(@Param("currentUser") User user);


}