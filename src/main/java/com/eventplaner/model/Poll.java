package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="poll")
public class Poll {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany//(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
    @JoinTable(
            name = "poll_organizers",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "user_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RegisteredUser> organizers;

    @OneToMany//(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
    @JoinTable(
            name = "poll_participants",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "user_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> participants;

    @OneToMany//(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
    @JoinTable(
            name = "stored_poll_topics",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "poll_id")},
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "id", name = "topic_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PollTopic> pollTopics;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "comment_system")
    private CommentSystem commentSystem;

    @Column(name="description")
    private String description;

    @Column(name="is_public")
    private boolean isPublic;

    public Poll(RegisteredUser organizer, String name, String description, boolean isPublic) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.isPublic = isPublic;
        this.name = name;

        this.commentSystem = new CommentSystem();
        this.organizers = new ArrayList<>();
        this.participants = new ArrayList<>();
        this.pollTopics = new ArrayList<>();

        this.organizers.add(organizer);
        this.addParticipant(organizer);
    }

    public Poll() {
        this.id = UUID.randomUUID().toString();
        this.commentSystem = new CommentSystem();
        this.organizers = new ArrayList<>();
        this.participants = new ArrayList<>();
        this.pollTopics = new ArrayList<>();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void addPollTopic(PollTopic topic){
        this.pollTopics.add(topic);
    }

    public void removePollTopic(PollTopic topic){
        this.pollTopics.remove(topic);
    }

    public void addParticipant(User participant){
        this.participants.add(participant);
    }

    public void removeParticipant(User participant){
        this.pollTopics.remove(participant);
    }

    public void addOrganizer(RegisteredUser organizer){
        this.organizers.add(organizer);
    }

    public void removeOrganizer(RegisteredUser organizer){
        this.organizers.remove(organizer);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<RegisteredUser> getOrganizers() {
        return organizers;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<PollTopic> getPollTopics() {
        return pollTopics;
    }

    public CommentSystem getCommentSystem() {
        return commentSystem;
    }

    public String getDescription() {
        return description;
    }

    public boolean getPublic() {
        return isPublic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrganizers(List<RegisteredUser> organizers) {
        this.organizers = organizers;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void setPollTopics(List<PollTopic> pollTopics) {
        this.pollTopics = pollTopics;
    }

    public void setCommentSystem(CommentSystem commentSystem) {
        this.commentSystem = commentSystem;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
