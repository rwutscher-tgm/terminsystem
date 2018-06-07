package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name="poll_topic")
public class PollTopic {

    @Id
    @Column(name="id")
    private String id;

    @Column
    private String description;

    @Column
    private ArrayList<User> availables;

    public PollTopic(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.availables = new ArrayList<>();
    }

    public PollTopic() {
        this.id = UUID.randomUUID().toString();
        this.availables = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getAvailables() {
        return availables;
    }

    public void addAvailable(User user) {
        this.availables.add(user);
    }

    public void removeAvailable(User user){
        this.availables.remove(user);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailables(ArrayList<User> availables) {
        this.availables = availables;
    }
}
