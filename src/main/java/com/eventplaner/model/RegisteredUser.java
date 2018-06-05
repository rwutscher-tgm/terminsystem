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


    public RegisteredUser(String email, String password, String username) {
        this.userID = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        this.password = hashedPassword;
    }

    public RegisteredUser(String userID, String email, String password, String username) {
        this.userID = userID;
        this.email = email;
        this.username = username;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        this.password = hashedPassword;
    }

    public RegisteredUser() {
        this.userID = UUID.randomUUID().toString();
    }

    public boolean isPassword(String unhashedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Password matches: " + passwordEncoder.matches(unhashedPassword, this.password));
        return passwordEncoder.matches(unhashedPassword, this.password);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        this.password = hashedPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
