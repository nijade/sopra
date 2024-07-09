package com.example.sopra.service;


import com.example.sopra.entity.Gender;
import com.example.sopra.entity.Plant;
import com.example.sopra.entity.User;
import com.example.sopra.repository.PlantRepository;
import com.example.sopra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PlantService plantService;

    @Autowired
    private PlantRepository plantRepository;

    /**
     * Konstruktor, der das Abhängigkeitsproblem löst, damit Spring die Merkliste verarbeiten kann.
     *
     * @param userRepository das repository zu Nutzer
     * @param plantService den Service für plants, wird mit @Lazy verzögert damit Abhängigkeit richtig laden kann
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PlantService plantService) {
        this.userRepository = userRepository;
        this.plantService = plantService;
    }

    /**
     * Gibt die Pflanzen in der Merkliste des übergebenen Users zurück und wirft exception falls user nicht gefunden wird.
     *
     * @param userId User, von dem die Merkliste abgefragt wird
     * @return verlinkt zu PlantService, der Logik enthält
     */
    @Transactional
    public List<Plant> getFavoritePlants(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
        List<Integer> favePlantIds = user.getFaves().stream().map(Plant::getPlantID).collect(Collectors.toList());
        return plantService.findAllByFavePlantIds(favePlantIds);
    }

    /**
     * Fügt eine Pflanze in die Merkliste eines Nutzers.
     *
     * @param userId User, zu dessen Merkliste etwas hinzugefügt wird
     * @param plantId Pflanze, die zur Merkliste hinzugefügt wird
     */
    @Transactional
    public void addToFavorites(Integer userId, Integer plantId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            Plant plant = plantService.findPlantByID(plantId); // Annahme: Methode findPlantById im PlantService vorhanden
            if (plant != null) {
                user.addToFavorites(plant);
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Plant with id " + plantId + " not found.");
            }
        } else {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
    }

    /**
     * Entfernt eine Pflanze von der Merkliste eines Nutzers.
     *
     * @param userId User, von dessen Merkliste etwas entfernt wird
     * @param plantId Pflanze, die von der Merkliste entfernt wird
     */
    @Transactional
    public void removeFromFavorites(Integer userId, Integer plantId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            Optional<Plant> plant = user.getFavoritePlants().stream().filter(p -> p.getPlantID().equals(plantId)).findFirst();
            plant.ifPresent(user::removeFromFavorites);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
    }

    /**
     * Fügt einen Benutzer zur Datenbank hinzu. (Entwickler-Option)
     * @param user Der neu hinzuzufügende Benutzer.
     * @return Übergibt Repository Anweisung zum Hinzufügen und gibt den entsprechenden Benutzer zurück.
     */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Aktualisiert die Informationen eines bestehenden Benutzers in der Datenbank.
     * @param user das Benutzerobjekt mit den aktualisierten Informationen
     * @return das aktualisierte Benutzerobjekt
     */
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Sucht nach einem User mit einem bestimmten Usernamen.
     *
     * @param username der username
     * @return User-Objekt
     */
    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Spring Security Authentication Methoden
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Gibt den aktuell eingeloggten User zurück.
     *
     * @return User
     */
    @Transactional
    public User getCurrentUser() {
        return getUserByUsername((((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername()));
    }

    /**
     * Gibt das UserDetails Objekt des aktuell eingeloggten Users zurück. Wird u.U. benötigt um
     * Rollen-Authentifizierungschecks durchzuführen.
     *
     * @return UserDetails Objekt der Spring Security
     */
    @Transactional
    public org.springframework.security.core.userdetails.User getCurrentUserDetails() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    /**
     * Überschreibt die Methode, welche für den Login der Spring Security benötigt wird..
     *
     * @param username the username des Benutzers
     * @return UserDetails Objekt des Spring Security Frameworks
     * @throws UsernameNotFoundException exception
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Could not find the user for username " + username);
        }
        List<GrantedAuthority> grantedAuthorities = getUserAuthorities();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, user.isEnabled(), grantedAuthorities);
    }

    // returning an empty list "grantedAuthorities" to match Constructors requirements
    private List<GrantedAuthority> getUserAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        return grantedAuthorities;
    }

    /**
     * Zu jedem Attribut das geändert werden soll wird geprüft, ob der geänderte Wert kein nicht Null ist. So wird
     * sichergestellt, dass sich nur Attribute ändern wenn deren Felder ausgefüllt sind. Die veränderten Attribute,
     * die in updatedUser übergeben werden, werden auf den currentUser überschrieben.
     * @param currentUser aktuell eingeloggter Benutzer
     * @param updatedUser Nachstellung eines Nutzers mit den veränderten Attributen
     */
    @Transactional
    public void updateUserProfile(User currentUser, User updatedUser) {
        if (updatedUser.getName() != null && !updatedUser.getName().isEmpty()) {
            currentUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            currentUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getAge() != null) {
            currentUser.setAge(updatedUser.getAge());
        }
        if (updatedUser.getGender() != null) {
            currentUser.setGender(updatedUser.getGender());
        }
        if (updatedUser.getProfileDescription() != null && !updatedUser.getProfileDescription().isEmpty()) {
            currentUser.setProfileDescription(updatedUser.getProfileDescription());
        }
        updateUser(currentUser);
    }

    @Transactional
    public List<User> sortBySales(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparingInt(User::getSales).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<User> sortByBuys(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparingInt(User::getBuys).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Diese Methode berechnet die Profilvollständigkeit anhand der bereits eingegebenen Daten des Nutzers.
     * Zu beachten gilt, das username und email nicht geprüft werden, da diese Pflicht sind, dass ein neuer acc existiert.
     *
     * @param user der User auf den die Profilvollständigkeit geprüft wird
     * @return die Profilvollständigkeit wird als ganze Zahl in Prozent zurückgegeben
     */
    public int calculateProfileCompletion(User user) {
        int totalFields = 5;
        int completedFields = 0;

        if (user.getName() != null && !user.getName().isEmpty()) completedFields++;
        if (user.getAge() != null) completedFields++;
        if (user.getGender() != Gender.EMPTY) completedFields++;
        if (user.getProfileDescription() != null && !user.getProfileDescription().isEmpty()) completedFields++;
        if (user.getProfileImage() != null && !user.getProfileImage().equals("default.jpg")) completedFields++;

        return (completedFields * 100) / totalFields;
    }

    public void addXp(User user, Integer xp){
        user.xp += xp;
        if(user.xp > user.xpToNextLevel){
            user.xp = user.xp - user.xpToNextLevel;
            user.level++;
            user.xpToNextLevel = user.calculateXpToNextLevel();
        }
        userRepository.save(user);
    }

    /**
     * Hiermit wird aktualisiert, welches Attribut öffentlich sichtbar sein soll.
     *
     * @param username User bei dem es aktualisiert wird
     * @param visibleAttributes neue Liste, die die alte überschreiben soll
     */
    public void updateVisibleAttributes(String username, List<String> visibleAttributes) {
        User user = userRepository.findByUsername(username);
        user.setVisibleAttributes(visibleAttributes);
        userRepository.save(user);
    }

}