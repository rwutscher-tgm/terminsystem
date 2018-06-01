package com.eventplaner.model;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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


    public RegisteredUser(String userid, String email, String password, String username) {
        this.email = email;
        this.userID = userID;
        this.username = username;

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String hashedPassword = passwordEncoder.encode(password);

        //System.out.println("password matches: "+passwordEncoder.matches(password, hashedPassword));

        //this.password = hashedPassword;
    }

    public RegisteredUser(String email, String username, String password) {
        this.email = email;
        this.username = username;

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String hashedPassword = passwordEncoder.encode(password);

        //System.out.println("password matches: "+passwordEncoder.matches(password, hashedPassword));

        //this.password = hashedPassword;
    }

    public RegisteredUser() {

    }

    public boolean isPassword(String unhashedPassword){
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //return passwordEncoder.matches(unhashedPassword, this.password);
        return true;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
