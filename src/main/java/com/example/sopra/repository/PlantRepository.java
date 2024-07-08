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

    //Specific category queries
    //Standard Query for finding Plants by searching for their title and Category
    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%')) AND :category MEMBER OF p.tags")
    List<Plant> findByTitleContainingIgnoreCaseSpecificCategory(@Param("title") String title, @Param("category") String category);

    @Query("SELECT p FROM Plant p WHERE p.plantID = :plantID")
    Plant findByPlantID(@Param("plantID") Integer plantID);

    @Query("SELECT p FROM Plant p")
    List<Plant> findAll();

    @Query("SELECT p FROM Plant p WHERE p.plantID IN :plantIds")
    List<Plant> findAllByIdIn(@Param("plantIds") List<Integer> plantIds);

    @Query("SELECT p FROM Plant p WHERE p.seller = :currentUser")
    List<Plant> findPlantsByUser(@Param("currentUser") User user);

    @Query("SELECT p FROM Plant p WHERE p.plantID IN :favePlantIds")
    List<Plant> findAllByFavePlantIds(@Param("favePlantIds") List<Integer> favePlantIds);

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))" +
            " AND (:priceMin IS NULL OR p.price >= :priceMin)" +
            " AND (:priceMax IS NULL OR p.price < :priceMax)" +
            " AND (:heightMin IS NULL OR p.height >= :heightMin)" +
            " AND (:heightMax IS NULL OR p.height < :heightMax)" +
            " AND (:circumferenceMin IS NULL OR p.plantCircumference >= :circumferenceMin)" +
            " AND (:circumferenceMax IS NULL OR p.plantCircumference < :circumferenceMax)")
    List<Plant> searchPlantsByTitleContainingIgnoreCaseAdditionalFilters(
            @Param("title") String title,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax,
            @Param("heightMin") Integer heightMin,
            @Param("heightMax") Integer heightMax,
            @Param("circumferenceMin") Double circumferenceMin,
            @Param("circumferenceMax") Double circumferenceMax);

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))" +
            " AND (:priceMinQueryReady IS NULL OR p.price >= :priceMinQueryReady)" +
            " AND (:priceMaxQueryReady IS NULL OR p.price < :priceMaxQueryReady)" +
            " AND (:heightMinQueryReady IS NULL OR p.height >= :heightMinQueryReady)" +
            " AND (:heightMaxQueryReady IS NULL OR p.height < :heightMaxQueryReady)" +
            " AND (:circumferenceMinQueryReady IS NULL OR p.plantCircumference >= :circumferenceMinQueryReady)" +
            " AND (:circumferenceMaxQueryReady IS NULL OR p.plantCircumference < :circumferenceMaxQueryReady)" +
            " ORDER BY p.price ASC")
    List<Plant> searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersPriceASC(
            @Param("title") String title,
            @Param("priceMinQueryReady") Double priceMinQueryReady,
            @Param("priceMaxQueryReady") Double priceMaxQueryReady,
            @Param("heightMinQueryReady") Integer heightMinQueryReady,
            @Param("heightMaxQueryReady") Integer heightMaxQueryReady,
            @Param("circumferenceMinQueryReady") Double circumferenceMinQueryReady,
            @Param("circumferenceMaxQueryReady") Double circumferenceMaxQueryReady);

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))" +
            " AND (:priceMinQueryReady IS NULL OR p.price >= :priceMinQueryReady)" +
            " AND (:priceMaxQueryReady IS NULL OR p.price < :priceMaxQueryReady)" +
            " AND (:heightMinQueryReady IS NULL OR p.height >= :heightMinQueryReady)" +
            " AND (:heightMaxQueryReady IS NULL OR p.height < :heightMaxQueryReady)" +
            " AND (:circumferenceMinQueryReady IS NULL OR p.plantCircumference >= :circumferenceMinQueryReady)" +
            " AND (:circumferenceMaxQueryReady IS NULL OR p.plantCircumference < :circumferenceMaxQueryReady)" +
            " ORDER BY p.price DESC")
    List<Plant> searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersPriceDSC(
            @Param("title") String title,
            @Param("priceMinQueryReady") Double priceMinQueryReady,
            @Param("priceMaxQueryReady") Double priceMaxQueryReady,
            @Param("heightMinQueryReady") Integer heightMinQueryReady,
            @Param("heightMaxQueryReady") Integer heightMaxQueryReady,
            @Param("circumferenceMinQueryReady") Double circumferenceMinQueryReady,
            @Param("circumferenceMaxQueryReady") Double circumferenceMaxQueryReady);

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))" +
            " AND (:category IS NULL OR :category MEMBER OF p.tags)" +
            " AND (:priceMinQueryReady IS NULL OR p.price >= :priceMinQueryReady)" +
            " AND (:priceMaxQueryReady IS NULL OR p.price < :priceMaxQueryReady)" +
            " AND (:heightMinQueryReady IS NULL OR p.height >= :heightMinQueryReady)" +
            " AND (:heightMaxQueryReady IS NULL OR p.height < :heightMaxQueryReady)" +
            " AND (:circumferenceMinQueryReady IS NULL OR p.plantCircumference >= :circumferenceMinQueryReady)" +
            " AND (:circumferenceMaxQueryReady IS NULL OR p.plantCircumference < :circumferenceMaxQueryReady)")
    List<Plant> searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersSpecificCategory(
            @Param("title") String title,
            @Param("category") String category,
            @Param("priceMinQueryReady") Double priceMinQueryReady,
            @Param("priceMaxQueryReady") Double priceMaxQueryReady,
            @Param("heightMinQueryReady") Integer heightMinQueryReady,
            @Param("heightMaxQueryReady") Integer heightMaxQueryReady,
            @Param("circumferenceMinQueryReady") Double circumferenceMinQueryReady,
            @Param("circumferenceMaxQueryReady") Double circumferenceMaxQueryReady);

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))" +
            " AND (:category IS NULL OR :category MEMBER OF p.tags)" +
            " AND (:priceMinQueryReady IS NULL OR p.price >= :priceMinQueryReady)" +
            " AND (:priceMaxQueryReady IS NULL OR p.price < :priceMaxQueryReady)" +
            " AND (:heightMinQueryReady IS NULL OR p.height >= :heightMinQueryReady)" +
            " AND (:heightMaxQueryReady IS NULL OR p.height < :heightMaxQueryReady)" +
            " AND (:circumferenceMinQueryReady IS NULL OR p.plantCircumference >= :circumferenceMinQueryReady)" +
            " AND (:circumferenceMaxQueryReady IS NULL OR p.plantCircumference < :circumferenceMaxQueryReady)" +
            " ORDER BY p.price ASC")
    List<Plant> searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersSpecificCategoryASC(
            @Param("title") String title,
            @Param("category") String category,
            @Param("priceMinQueryReady") Double priceMinQueryReady,
            @Param("priceMaxQueryReady") Double priceMaxQueryReady,
            @Param("heightMinQueryReady") Integer heightMinQueryReady,
            @Param("heightMaxQueryReady") Integer heightMaxQueryReady,
            @Param("circumferenceMinQueryReady") Double circumferenceMinQueryReady,
            @Param("circumferenceMaxQueryReady") Double circumferenceMaxQueryReady);

    @Query("SELECT p FROM Plant p WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :title, '%'))" +
            " AND (:category IS NULL OR :category MEMBER OF p.tags)" +
            " AND (:priceMinQueryReady IS NULL OR p.price >= :priceMinQueryReady)" +
            " AND (:priceMaxQueryReady IS NULL OR p.price < :priceMaxQueryReady)" +
            " AND (:heightMinQueryReady IS NULL OR p.height >= :heightMinQueryReady)" +
            " AND (:heightMaxQueryReady IS NULL OR p.height < :heightMaxQueryReady)" +
            " AND (:circumferenceMinQueryReady IS NULL OR p.plantCircumference >= :circumferenceMinQueryReady)" +
            " AND (:circumferenceMaxQueryReady IS NULL OR p.plantCircumference < :circumferenceMaxQueryReady)" +
            " ORDER BY p.price DESC")
    List<Plant> searchPlantsByTitleContainingIgnoreCaseAdditionalFiltersSpecificCategoryDSC(
            @Param("title") String title,
            @Param("category") String category,
            @Param("priceMinQueryReady") Double priceMinQueryReady,
            @Param("priceMaxQueryReady") Double priceMaxQueryReady,
            @Param("heightMinQueryReady") Integer heightMinQueryReady,
            @Param("heightMaxQueryReady") Integer heightMaxQueryReady,
            @Param("circumferenceMinQueryReady") Double circumferenceMinQueryReady,
            @Param("circumferenceMaxQueryReady") Double circumferenceMaxQueryReady);

    List<Plant> findAllByBuyerFinal(User buyer);
}