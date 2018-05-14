package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="poll")
public class Poll {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name="organizers")
    private ArrayList<RegisteredUser> organizers;

    @Column(name="participants")
    private ArrayList<User> participants;

    @Column(name="pollTopics")
    private ArrayList<PollTopic> pollTopics;

    //@Column(name="commentSystem")
    private CommentSystem commentSystem;

    @Column(name="description")
    private String description;

    @Column(name="isPublic")
    private boolean isPublic;

    public Poll(String name, String description, boolean isPublic) {
        this.description = description;
        this.isPublic = isPublic;
        this.name = name;

        this.commentSystem = new CommentSystem();
        this.organizers = new ArrayList<>();
        this.participants = new ArrayList<>();

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<RegisteredUser> getOrganizers() {
        return organizers;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public ArrayList<PollTopic> getPollTopics() {
        return pollTopics;
    }

    public CommentSystem getCommentSystem() {
        return commentSystem;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
