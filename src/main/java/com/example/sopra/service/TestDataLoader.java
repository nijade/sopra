package com.example.sopra.service;

import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Initialisiere Testdaten auf Datenbank...");

        // Nutzer 1
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("1234"));
        userService.saveUser(user1);
        // Nutzer 2
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("5678"));
        userService.saveUser(user2);

        // Nutzer 3
        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword(passwordEncoder.encode("abcd"));
        userService.saveUser(user3);

        // Nutzer 4
        User user4 = new User();
        user4.setUsername("user4");
        user4.setPassword(passwordEncoder.encode("efgh"));
        userService.saveUser(user4);

        // Nutzer 5
        User user5 = new User();
        user5.setUsername("user5");
        user5.setPassword(passwordEncoder.encode("ijkl"));
        userService.saveUser(user5);

        // Nutzer 6
        User hans = new User();
        hans.setUsername("Hans");
        hans.setPassword(passwordEncoder.encode("0987"));
        hans.setName("Hans Bauer");
        hans.setEmail("bauer@exmaple.com");
        hans.setAge(33);
        hans.setGender("Male");
        hans.setProfileDescription("aufgewachsen in stuttgart, Sicherheitsingenieur, Naturfreund");
        hans.setProfileImage("profilbild_schafStrecktZunge.jpeg");
        userService.saveUser(hans);


        //Beispielpflanzen ***********************************************************************+
        // Beispielpflanze: Rose

        Plant rose = new Plant();
        rose.setTitle("rote Rose");
        rose.setPhotos(Arrays.asList("rose.jpg"));
        rose.setHeight(50);
        rose.setPrice(15.99);
        rose.setHasPlanter(true);
        rose.setDescription("Eine wunderschöne rote Rose.");
        rose.setPotCircumference(20.0);
        rose.setPlantCircumference(30.0);
        rose.setTags(Arrays.asList("Sommerblumen", "Rosen", "Garten"));
        rose.setSeller(user1);
        plantService.savePlant(rose);

        // Tulpen
        // Tulpe groß
        Plant tulip = new Plant();
        tulip.setTitle("Tulpe");
        tulip.setPhotos(Arrays.asList("tulip.jpg"));
        tulip.setHeight(30);
        tulip.setPrice(8.99);
        tulip.setHasPlanter(false);
        tulip.setDescription("Eine bunte Tulpe, ideal für den Frühlingsgarten.");
        tulip.setPotCircumference(0.0);
        tulip.setPlantCircumference(10.0);
        tulip.setTags(Arrays.asList("Blume", "Frühling", "Garten"));
        tulip.setSeller(user2);
        plantService.savePlant(tulip);

        // Tulpe medium
        Plant tulipMedium = new Plant();
        tulipMedium.setTitle("Tulpe Medium");
        tulipMedium.setPhotos(Arrays.asList("tulip.jpg"));
        tulipMedium.setHeight(20);
        tulipMedium.setPrice(8.99);
        tulipMedium.setHasPlanter(false);
        tulipMedium.setDescription("Eine bunte medium Tulpe, ideal für den Frühlingsgarten.");
        tulipMedium.setPotCircumference(0.0);
        tulipMedium.setPlantCircumference(10.0);
        tulipMedium.setTags(Arrays.asList("Blume", "Frühling", "Garten"));
        tulipMedium.setSeller(user2);
        plantService.savePlant(tulipMedium);

        // Weiße Spätblühende Tulpe
        Plant tulipThree = new Plant();
        tulipThree.setTitle("Weiße Spätblühende Tulpe");
        tulipThree.setPhotos(Arrays.asList("tulip.jpg"));
        tulipThree.setHeight(45);
        tulipThree.setPrice(12.99);
        tulipThree.setHasPlanter(true);
        tulipThree.setDescription("Eine spätblühende weiße Tulpe, perfekt für den Spätsommergarten.");
        tulipThree.setPotCircumference(12.0); // Beispiel für den Pflanztopfumfang
        tulipThree.setPlantCircumference(13.5);
        tulipThree.setTags(Arrays.asList("Blume", "Sommer", "Garten"));
        tulipThree.setSeller(user1);
        plantService.savePlant(tulipThree);

       // Pinkfarbene Zwergtulpe
        Plant tulipFour = new Plant();
        tulipFour.setTitle("Pinkfarbene Zwergtulpe");
        tulipFour.setPhotos(Arrays.asList("tulip.jpg"));
        tulipFour.setHeight(20);
        tulipFour.setPrice(7.49);
        tulipFour.setHasPlanter(true);
        tulipFour.setDescription("Eine niedrige pinkfarbene Zwergtulpe, ideal für den Vorgarten.");
        tulipFour.setPotCircumference(8.0); // Beispiel für den Pflanztopfumfang
        tulipFour.setPlantCircumference(8.0);
        tulipFour.setTags(Arrays.asList("Blume", "Frühling", "Zwiebel- und Knollenpflanzen"));
        tulipFour.setSeller(user2);
        plantService.savePlant(tulipFour);

        // Orangene Darwin-Tulpe
        Plant tulipFive = new Plant();
        tulipFive.setTitle("Orangene Darwin-Tulpe");
        tulipFive.setPhotos(Arrays.asList("tulip.jpg"));
        tulipFive.setHeight(38);
        tulipFive.setPrice(11.29);
        tulipFive.setHasPlanter(false);
        tulipFive.setDescription("Eine kräftige orangefarbene Darwin-Tulpe, die im Frühling aufblüht.");
        tulipFive.setPotCircumference(0.0);
        tulipFive.setPlantCircumference(11.0);
        tulipFive.setTags(Arrays.asList("Blume", "Frühling", "Garten"));
        tulipFive.setSeller(user1);
        plantService.savePlant(tulipFive);

        // Gefüllte Lilien-Tulpe
        Plant tulipSix = new Plant();
        tulipSix.setTitle("Gefüllte Lilien-Tulpe");
        tulipSix.setPhotos(Arrays.asList("tulip.jpg"));
        tulipSix.setHeight(32);
        tulipSix.setPrice(9.99);
        tulipSix.setHasPlanter(true);
        tulipSix.setDescription("Eine prächtige gefüllte Lilien-Tulpe, die im Frühling besonders schön blüht.");
        tulipSix.setPotCircumference(10.0); // Beispiel für den Pflanztopfumfang
        tulipSix.setPlantCircumference(10.5);
        tulipSix.setTags(Arrays.asList("Blume", "Frühling", "Zwiebel- und Knollenpflanzen"));
        tulipSix.setSeller(user2);
        plantService.savePlant(tulipSix);

        // Zweifarbige Rembrandt-Tulpe
        Plant tulipSeven = new Plant();
        tulipSeven.setTitle("Zweifarbige Rembrandt-Tulpe");
        tulipSeven.setPhotos(Arrays.asList("tulip.jpg"));
        tulipSeven.setHeight(40);
        tulipSeven.setPrice(10.79);
        tulipSeven.setHasPlanter(false);
        tulipSeven.setDescription("Eine elegante zweifarbige Rembrandt-Tulpe, die im Mai blüht.");
        tulipSeven.setPotCircumference(0.0);
        tulipSeven.setPlantCircumference(12.0);
        tulipSeven.setTags(Arrays.asList("Blume", "Sommerblumen", "Garten"));
        tulipSeven.setSeller(user1);
        plantService.savePlant(tulipSeven);

        // Gefranste Papageien-Tulpe
        Plant tulipEight = new Plant();
        tulipEight.setTitle("Gefranste Papageien-Tulpe");
        tulipEight.setPhotos(Arrays.asList("tulip.jpg"));
        tulipEight.setHeight(36);
        tulipEight.setPrice(11.59);
        tulipEight.setHasPlanter(true);
        tulipEight.setDescription("Eine spektakuläre gefranste Papageien-Tulpe, die im Frühling für Aufsehen sorgt.");
        tulipEight.setPotCircumference(12.0); // Beispiel für den Pflanztopfumfang
        tulipEight.setPlantCircumference(12.5);
        tulipEight.setTags(Arrays.asList("Blume", "Frühling", "Zwiebel- und Knollenpflanzen"));
        tulipEight.setSeller(user2);
        plantService.savePlant(tulipEight);

        // Gelbe Triumph-Tulpe
        Plant tulipNine = new Plant();
        tulipNine.setTitle("Gelbe Triumph-Tulpe");
        tulipNine.setPhotos(Arrays.asList("tulip.jpg"));
        tulipNine.setHeight(30);
        tulipNine.setPrice(9.79);
        tulipNine.setHasPlanter(false);
        tulipNine.setDescription("Eine leuchtende gelbe Triumph-Tulpe, die im April blüht.");
        tulipNine.setPotCircumference(0.0);
        tulipNine.setPlantCircumference(10.0);
        tulipNine.setTags(Arrays.asList("Blume", "Frühling", "Gemüse"));
        tulipNine.setSeller(user1);
        plantService.savePlant(tulipNine);

        // Weiße Gefüllte Viridiflora-Tulpe
        Plant tulipTen = new Plant();
        tulipTen.setTitle("Weiße Gefüllte Viridiflora-Tulpe");
        tulipTen.setPhotos(Arrays.asList("tulip.jpg"));
        tulipTen.setHeight(35);
        tulipTen.setPrice(10.49);
        tulipTen.setHasPlanter(true);
        tulipTen.setDescription("Eine elegante weiße gefüllte Viridiflora-Tulpe mit grünen Akzenten.");
        tulipTen.setPotCircumference(10.0); // Beispiel für den Pflanztopfumfang
        tulipTen.setPlantCircumference(11.0);
        tulipTen.setTags(Arrays.asList("Blume", "Frühling", "Zwiebel- und Knollenpflanzen"));
        tulipTen.setSeller(user2);
        plantService.savePlant(tulipTen);

        // Kaktus
        Plant cactus = new Plant();
        cactus.setTitle("Kakteen Echinopsis Lobivia cacti hybrid Lobivia cactus");
        cactus.setPhotos(Arrays.asList("cactus.jpg", "cactus2.jpg"));
        cactus.setHeight(40);
        cactus.setPrice(90.49);
        cactus.setHasPlanter(true);
        cactus.setDescription("");
        cactus.setPotCircumference(30.0);
        cactus.setPlantCircumference(20.0);
        cactus.setTags(Arrays.asList("Kübelpflanzen", "Sommerblumen", "Zimmerpflanzen"));
        cactus.setSeller(user3);
        plantService.savePlant(cactus);

        // Palme
        Plant palme = new Plant();
        palme.setTitle("Yucca Palme");
        palme.setPhotos(Arrays.asList("yucca-palme.jpg"));
        palme.setHeight(110);
        palme.setPrice(40.00);
        palme.setHasPlanter(true);
        palme.setDescription("Die Studenten-Pflanze: Pflegeleicht und prachtvoll.");
        palme.setPotCircumference(36.0);
        palme.setPlantCircumference(70.0);
        palme.setTags(Arrays.asList("Bäume und Sträucher", "Sommerblumen", "Zimmerpflanzen"));
        palme.setSeller(hans);
        plantService.savePlant(palme);


        //*********************************************************************************
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
