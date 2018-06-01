package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.tasks.Task;

public class AddComment implements Task{

    private CommentSystem system;
    private Comment comment;

    public AddComment(CommentSystem system, Comment comment){
        this.comment = comment;
        this.system = system;
    }

    @Override
    public void execute() {

    }
}
