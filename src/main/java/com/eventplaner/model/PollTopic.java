package com.eventplaner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="poll_topic")
public class PollTopic {

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
