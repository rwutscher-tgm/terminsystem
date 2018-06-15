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
     * Gibt den Author des Kommentars zurück
     * @return den Author des Kommentars
     */
    public RegisteredUser getAuthor() {
        return author;
    }

    /**
     * Gibt das Erstellungsdatum zurück
     * @return das Datum, an welchem der Kommentar erstellt wurde, als String in Form von "dd.MM.yyyy"
     */
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        return formatter.format(dateTime);
    }

    /**
     * Gibt die Erstellungszeit zurück
     * @return die Uhrzeit, zu welcher der Kommentar erstellt wurde, als String in Form von "HH:mm:ss"
     */
    public String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        return formatter.format(dateTime);
    }

    /**
     * Gibt das Erstellungsdatum und die Erstellungsuhrzeit zurück
     * @return die DateTime, an welcher der Kommentar erstellt wurde
     */
    public Date getDateTime(){
        return dateTime;
    }

    /**
     * Gibt das Erstellungsdatum und die Erstellungsuhrzeit als String zurück
     * @return die DateTime, an welcher der Kommentar erstellt wurde , als String in Form von "dd.MM.yyyy HH:mm:ss"
     */
    public String getDateTimeString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        return formatter.format(dateTime);
    }

    /**
     * Gibt die ID des Kommentars zurück
     * @return die Id des Kommentars
     */
    public String getCommentID() {
        return commentID;
    }

    /**
     * Gibt den Text des Kommentars zurück
     * @return den Text des Kommentars
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gibt das Kommentarsystem, in welchem die Antworten auf diesen Kommentar gespeichert werden, zurück
     * @return das Kommentarsystem innerhalb des Subsystems
     */
    public CommentSystem getSubCommentSystem() {
        return subCommentSystem;
    }

    /**
     * Setzt die Id des Kommentars
     * @param commentID die neue Id des Kommentars
     */
    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    /**
     * Setzt den Author des Kommentars
     * @param author der registrierte Benutzer, welcher den Kommentar verfasst hat
     */
    public void setAuthor(RegisteredUser author) {
        this.author = author;
    }

    /**
     * Setzt die DateTime, zu welcher der Kommentar erstellt wurde
     * @param dateTime die DateTime des Kommentars
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Setzt den Text des Kommentars
     * @param comment der Text des Kommentars
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Setzt des SubCommentSystem des Kommentars
     * @param subCommentSystem das neue SubCommentSystems
     */
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

