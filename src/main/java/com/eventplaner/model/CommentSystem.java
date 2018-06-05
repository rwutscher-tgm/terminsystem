package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="comment_system")
public class CommentSystem {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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

    public CommentSystem() {
        comments = new ArrayList<>();

    }

    public void addComment(Comment c){
        comments.add(c);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCommentSystemID() {
        return commentSystemID;
    }

    public void setCommentSystemID(String commentSystemID) {
        this.commentSystemID = commentSystemID;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
