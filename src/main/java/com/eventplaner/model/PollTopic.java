package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="poll_topic")
public class PollTopic {

    @Id
    @Column(name="id")
    private String id;

    @Column
    private String description;

    @OneToMany//(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
    @JoinTable(
            name = "poll_topic_availables",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "poll_topic_id")},
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "user_id", name = "user_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> availables;

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

    public List<User> getAvailables() {
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
