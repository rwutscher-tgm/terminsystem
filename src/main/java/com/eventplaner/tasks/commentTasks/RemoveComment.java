package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.tasks.Task;

public class RemoveComment implements Task{

    private Comment comment;
    private CommentRepository commentRepository;

    public RemoveComment(Comment comment, CommentRepository commentRepository) {
        this.comment = comment;
        this.commentRepository = commentRepository;
    }

    @Override
    public void execute() {
        commentRepository.delete(comment);
//        new DeleteObject<>(this.comment);
    }
}