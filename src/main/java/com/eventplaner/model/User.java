package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="user")
public abstract class User {

    @Id
    @Column(name="user_id")
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected String  userID;

    @Column(name="email")
    protected String email;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return userID.equals(user.userID);
    }

    @Override
    public int hashCode() {
        return userID.hashCode();
    }
}
