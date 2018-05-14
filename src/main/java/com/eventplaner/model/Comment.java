package com.eventplaner.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @Column(name="comment_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String  commentID;

    //@Column(name="author")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegisteredUser author;

    @Column(name="date")
    private String date;

    @Column(name="time")
    private String time;

    @Column(name="comment")
    private String comment;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sub_comment_system")
    private CommentSystem subCommentSystem;

    public Comment(RegisteredUser author, String date, String time, String comment) {
        this.author = author;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.subCommentSystem = new CommentSystem();
    }

    public RegisteredUser getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public CommentSystem getSubComments() {
        return subCommentSystem;
    }
}
