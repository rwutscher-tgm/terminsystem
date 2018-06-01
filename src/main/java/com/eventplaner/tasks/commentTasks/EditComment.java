package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.tasks.Task;

public class EditComment implements Task{

    private Comment comment;
    private String newText;

    public EditComment(Comment comment, String newText) {
        this.comment = comment;
        this.newText = newText;
    }


    @Override
    public void execute() {

    }
}
