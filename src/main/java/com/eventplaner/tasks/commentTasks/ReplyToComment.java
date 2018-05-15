package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.tasks.Task;

public class ReplyToComment implements Task{

    private Comment parent;
    private Comment child;

    public ReplyToComment(Comment parent, Comment child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void execute() {

    }
}
