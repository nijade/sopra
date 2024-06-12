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
        User Hans = new User();
        Hans.setUsername("Hans");
        Hans.setPassword(passwordEncoder.encode("0987"));
        Hans.setName("Hans Bauer");
        Hans.setEmail("bauer@exmaple.com");
        //Hans.setPhoto("");
        Hans.setAge(33);
        Hans.setGender("Male");
        Hans.setProfileDescription("aufgewachsen in stuttgart, Sicherheitsingenieur, Naturfreund");
        Hans.setProfileImage("profilbild_schafStrecktZunge.jpeg");
        userService.saveUser(Hans);

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
        rose.setTags(Arrays.asList("blume", "garten", "romantisch"));
        rose.setUser(user1);

        plantService.savePlant(rose);

        // Beispielpflanze: Tulpe groß
        Plant tulip = new Plant();
        tulip.setTitle("Tulpe");
        tulip.setPhotos(Arrays.asList("tulip.jpg"));
        tulip.setHeight(30);
        tulip.setPrice(8.99);
        tulip.setHasPlanter(false);
        tulip.setDescription("Eine bunte Tulpe, ideal für den Frühlingsgarten.");
        tulip.setPotCircumference(0.0);
        tulip.setPlantCircumference(10.0);
        tulip.setTags(Arrays.asList("blume", "frühling", "garten"));
        tulip.setUser(user2);

        plantService.savePlant(tulip);

        // Beispielpflanze: Tulpe medium
        Plant tulipMedium = new Plant();
        tulipMedium.setTitle("Tulpe Medium");
        tulipMedium.setPhotos(Arrays.asList("tulip.jpg"));
        tulipMedium.setHeight(20);
        tulipMedium.setPrice(8.99);
        tulipMedium.setHasPlanter(false);
        tulipMedium.setDescription("Eine bunte medium Tulpe, ideal für den Frühlingsgarten.");
        tulipMedium.setPotCircumference(0.0);
        tulipMedium.setPlantCircumference(10.0);
        tulipMedium.setTags(Arrays.asList("blume", "frühling", "garten"));
        tulipMedium.setUser(user2);


        plantService.savePlant(tulipMedium);

        // Beispielpflanze: Kaktus
        Plant cactus = new Plant();
        cactus.setTitle("Kakteen Echinopsis Lobivia cacti hybrid Lobivia cactus");
        cactus.setPhotos(Arrays.asList("cactus.jpg", "cactus2.jpg"));
        cactus.setHeight(40);
        cactus.setPrice(90.49);
        cactus.setHasPlanter(true);
        cactus.setDescription("");
        cactus.setPotCircumference(30.0);
        cactus.setPlantCircumference(20.0);
        cactus.setTags(Arrays.asList("sukkulent", "wohnung", "pflegeleicht"));
        cactus.setUser(user3);

        plantService.savePlant(cactus);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
