package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.model.repositories.CommentSystemRepository;
import com.eventplaner.tasks.Task;

public class EditComment implements Task{

    private Comment comment;
    private String newText;
    private CommentRepository commentRepository;


    public EditComment(Comment comment, String newText, CommentRepository commentRepository) {
        this.comment = comment;
        this.newText = newText;
        this.commentRepository = commentRepository;
    }


    @Override
    public void execute() {
        comment.setComment(newText);
        commentRepository.save(comment);
    }
}
