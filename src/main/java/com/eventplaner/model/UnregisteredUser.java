package com.eventplaner.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="unregistered_user")
public class UnregisteredUser extends User{

    public UnregisteredUser(String email){
        this.userID = UUID.randomUUID().toString();
        this.email = email;
    }

    public UnregisteredUser() {
        this.userID = UUID.randomUUID().toString();
    }
}
