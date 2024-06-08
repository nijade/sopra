package com.example.sopra.service;


import com.example.sopra.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public com.example.sopra.entity.User saveUser(com.example.sopra.entity.User user) {
        return userRepository.save(user);
    }

    /**
     * Aktualisiert die Informationen eines bestehenden Benutzers in der Datenbank.
     * @param user das Benutzerobjekt mit den aktualisierten Informationen
     * @return das aktualisierte Benutzerobjekt
     */
    @Transactional
    public com.example.sopra.entity.User updateUser(com.example.sopra.entity.User user) {
        return userRepository.save(user);
    }

    public List<com.example.sopra.entity.User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Sucht nach einem User mit einem bestimmten Usernamen.
     *
     * @param username der username
     * @return User-Objekt
     */
    public com.example.sopra.entity.User getUserByUsername(String username) {
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
    public com.example.sopra.entity.User getCurrentUser() {
        return getUserByUsername((((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername()));
    }

    /**
     * Gibt das UserDetails Objekt des aktuell eingeloggten Users zurück. Wird u.U. benötigt um
     * Rollen-Authentifizierungschecks durchzuführen.
     *
     * @return UserDetails Objekt der Spring Security
     */
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.sopra.entity.User user = userRepository.findByUsername(username);
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

}