package com.example.sopra.service;

import com.example.sopra.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

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
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
