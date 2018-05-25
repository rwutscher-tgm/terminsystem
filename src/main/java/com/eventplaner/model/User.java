package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="user")
public abstract class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String  userID;

    @Column(name="email")
    private String email;

    public User(String email){
        this.email = email;
    }

    public User(String email, String  userID){
        this.email = email;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
