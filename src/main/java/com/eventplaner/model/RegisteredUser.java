package com.eventplaner.model;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="registered_user")
public class RegisteredUser extends User {

    @Column (name="password")
    private String password;

    @Column(name="username")
    private String username;

    /**
     * Dieser Konstruktor nimmt drei Parameter entgegen um einen registrieten User zu erstellen
     * @param email die Email des registrierten Benutzers
     * @param password das Passwort des registrierten Benutzer
     * @param username der Username des registrierten Benutzer
     */
    public RegisteredUser(String email, String password, String username) {
        this.userID = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        this.password = hashedPassword;
    }

    /**
     * Dieser Konstruktor nimmt für jedes der vier Attribute einen Parameter um einen registrierten User zu erstellen
     * @param userID die Id des registrierten Benutzers
     * @param email die Email des registrierten Benutzers
     * @param password das Passwort des registrierten Benutzer
     * @param username der Username des registrierten Benutzer
     */
    public RegisteredUser(String userID, String email, String password, String username) {
        this.userID = userID;
        this.email = email;
        this.username = username;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        this.password = hashedPassword;
    }

    /**
     * Der no-args Konstruktor von RegisteredUser
     */
    public RegisteredUser() {
        this.userID = UUID.randomUUID().toString();
    }

//    public boolean isPassword(String unhashedPassword){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println("Password matches: " + passwordEncoder.matches(unhashedPassword, this.password));
//        return passwordEncoder.matches(unhashedPassword, this.password);
//    }

    /**
     * Gibt das Passwort des Benutzers zurück
     * @return das verschlüsselte Passwort des Benutzers
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gibt den Username des Benutzers zurück
     * @return den Benutzernamen des Benutzers
     */
    public String getUsername() {
        return username;
    }

    /**
     * Nimmt einen String als Passwort entgegen, verschlüsselt ihn und speichert das Passwort dann
     * @param password das neue Passwort
     */
    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        this.password = hashedPassword;
    }

    /**
     * Setzt den Username des Benutzers
     * @param username der neue Username
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
