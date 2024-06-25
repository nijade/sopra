package com.example.sopra.service;


import com.example.sopra.entity.User;
import com.example.sopra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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
        if (updatedUser.getGender() != null && !updatedUser.getGender().isEmpty()) {
            currentUser.setGender(updatedUser.getGender());
        }
        if (updatedUser.getProfileDescription() != null && !updatedUser.getProfileDescription().isEmpty()) {
            currentUser.setProfileDescription(updatedUser.getProfileDescription());
        }
        updateUser(currentUser);
    }

}