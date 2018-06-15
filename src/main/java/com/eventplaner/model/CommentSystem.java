package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * CommentSystems sind Entities, welche Kommentare enthält, welche daraufhin wieder eigene Commentsystems beinhalten.
 * @author Nemanja Tesanovic
 */

@Entity
@Table(name="comment_system")
public class CommentSystem {

    @Id
    @Column(name="comment_system_id")
    private String  commentSystemID;

    //@Column(name = "comments")
    @OneToMany//(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
    @JoinTable(
            name = "stored_comment",
            joinColumns = {@JoinColumn(referencedColumnName = "comment_system_id", name = "comment_system_id")},
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "comment_id", name = "comment_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;

    /**
     * Der no-args Konstruktor der CommentSystem-Klasse
     */
    public CommentSystem() {
        this.commentSystemID = UUID.randomUUID().toString();
        comments = new ArrayList<>();

    }

    /**
     * Fügt einen Kommentar zum CommentSystem hinzu bzw. fügt es in die Liste der Kommentare hinzu
     * @param c der neue Kommentar
     */
    public void addComment(Comment c){
        comments.add(c);
    }

    /**
     * Gibt die Liste zurück, welche die Kommentare beinhaltet
     * @return die Liste der Kommentare
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Gibt die Id des CommentSystems zurück
     * @return die Id des CommentSystems
     */
    public String getCommentSystemID() {
        return commentSystemID;
    }

    /**
     * Setzt die Id des CommentSystems
     * @param commentSystemID die neue Id des CommentSystems
     */
    public void setCommentSystemID(String commentSystemID) {
        this.commentSystemID = commentSystemID;
    }

    /**
     * Setzt die Liste der Kommentare des CommentSystems
     * @param comments die neue Liste von Kommentare
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Die equals-Methode, welche 2 CommentSystems mittels der Id vergleicht
     * @param o das Objekt, mit welchem eine Instanz des CommentSystems verglichen werden soll
     * @return das Ergbnis des Vergleichs
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentSystem)) return false;

        CommentSystem that = (CommentSystem) o;

        return commentSystemID.equals(that.commentSystemID);
    }

    /**
     * Generiert den HashCode des Objektes mithilfe der Id
     * @return der generierte HashCode
     */
    @Override
    public int hashCode() {
        return commentSystemID.hashCode();
    }
}
