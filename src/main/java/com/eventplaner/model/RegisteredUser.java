package com.eventplaner.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="registered_user")
public class RegisteredUser extends User {

    @Column (name="password")
    private String password;

    @Column(name="username")
    private String username;

    public RegisteredUser(String email, String username, String password) {
        super(email);
        this.username = username;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        //System.out.println("password matches: "+passwordEncoder.matches(password, hashedPassword));

        this.password = hashedPassword;
    }

    public boolean isPassword(String unhashedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(unhashedPassword, this.password);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
