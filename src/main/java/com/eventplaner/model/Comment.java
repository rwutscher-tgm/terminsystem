package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Diese Klasse ist eine Entity, welche alle mit einem Kommentar verwandten Attribute speichert.
 * @author Richard Wutscher
 */
@Entity
@Table(name="comment")
public class Comment {


    @Id
    //@GeneratedValue
    @Column(name="comment_id")
    private String  commentID;

    //@Column(name="author")
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private RegisteredUser author;

    @Column(name="date_time")
    private Date dateTime;

    @Column(name="comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "sub_comment_system")
    private CommentSystem subCommentSystem;

    /**
     * Der Konstruktor für die Comment Klasse
     * @param author Der ersteller des Kommentars
     * @param comment Der Text des Kommentars
     */
    public Comment(RegisteredUser author, String comment/*, CommentSystem subComments*/) {
        this.commentID = UUID.randomUUID().toString();
        this.author = author;
        this.comment = comment;
        //this.subCommentSystem = subComments;
        //this.subCommentSystem = new CommentSystem();

        this.dateTime = new Date();
    }

    /**
     * Der no-args Konstruktor für die Comment Klasse
     */
    public Comment() {
        this.commentID = UUID.randomUUID().toString();
        //this.commentID = UUID.randomUUID().toString();
 //       this.subCommentSystem = new CommentSystem();
        //this.subCommentSystem = new CommentSystem();
    }

    /**
     *
     * @return Gibt den Author des Kommentars zurück
     */
    public RegisteredUser getAuthor() {
        return author;
    }

    /**
     *
     * @return Gibt das Erstellungsdatum zurück
     */
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        return formatter.format(dateTime);
    }

    /**
     *
     * @return Gibt die Erstellungszeit zurück
     */
    public String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        return formatter.format(dateTime);
    }

    /**
     *
     * @return Gibt das Erstellungsdatum und die Erstellungsuhrzeit zurück
     */
    public Date getDateTime(){
        return dateTime;
    }

    /**
     *
     * @return Gibt das Erstellungsdatum und die Erstellungsuhrzeit als String zurück
     */
    public String getDateTimeString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        return formatter.format(dateTime);
    }

    /**
     *
     * @return Gibt die ID des Kommentars zurück
     */
    public String getCommentID() {
        return commentID;
    }

    /**
     *
     * @return Gibt den Text des Kommentars zurück
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @return Gibt das Kommentarsystem indem die Antworten auf diesen Kommentar gespeichert werden zurück
     */
    public CommentSystem getSubCommentSystem() {
        return subCommentSystem;
    }


    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public void setAuthor(RegisteredUser author) {
        this.author = author;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSubCommentSystem(CommentSystem subCommentSystem) {
        this.subCommentSystem = subCommentSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        return commentID.equals(comment.commentID);
    }

    @Override
    public int hashCode() {
        return commentID.hashCode();
    }
}

