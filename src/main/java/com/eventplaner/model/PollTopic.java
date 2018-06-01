package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="poll_topic")
public class PollTopic {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String description;

    @Column
    private ArrayList<User> availables;

    public PollTopic(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getAvailables() {
        return availables;
    }
}
