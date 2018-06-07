package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.tasks.Task;

public class RemoveComment implements Task{

    private Comment comment;

    public RemoveComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public void execute() {
//        new DeleteObject<>(this.comment);
    }
}