package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;

public class AddComment implements Task{

    private CommentSystem system;
    private RegisteredUser author;
    private String comment;

    public AddComment(CommentSystem system, RegisteredUser author, String comment){
        this.system = system;
        this.author = author;
        this.comment = comment;
    }

    public AddComment(Poll poll, RegisteredUser author, String comment){
        this.system = poll.getCommentSystem();
        this.author = author;
        this.comment = comment;
    }

    @Override
    public void execute() {
        Comment c = new Comment(this.author, this.comment);
        this.system.addComment(c);
        new SaveObject(c).execute();
        new SaveObject(this.system).execute();
    }
}
