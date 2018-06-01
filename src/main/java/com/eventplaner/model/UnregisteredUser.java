package com.eventplaner.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="unregistered_user")
public class UnregisteredUser extends User{

    public UnregisteredUser(String email){
        this.email = email;
    }

    public UnregisteredUser(String email, String  userID){
        this.email = email;
        this.userID = userID;
    }

}
