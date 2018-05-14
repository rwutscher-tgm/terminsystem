package com.eventplaner.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="unregistered_user")
public class UnregisteredUser extends User{

    public UnregisteredUser(String email) {
        super(email);
    }
}
