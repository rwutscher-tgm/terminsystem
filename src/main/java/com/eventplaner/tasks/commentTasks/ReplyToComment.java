package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.model.repositories.CommentSystemRepository;
import com.eventplaner.tasks.Task;

/**
 * Fügt eine Antwort zu einem Kommentar hinzu
 */
public class ReplyToComment implements Task{

    private Comment parent;
    private Comment child;
    private CommentRepository commentRepository;
    private CommentSystemRepository commentSystemRepository;

    public ReplyToComment(Comment parent, Comment child, CommentRepository commentRepository, CommentSystemRepository commentSystemRepository) {
        this.parent = parent;
        this.child = child;
        this.commentRepository = commentRepository;
        this.commentSystemRepository = commentSystemRepository;
    }

    @Override
    public void execute() {
        parent.getSubCommentSystem().addComment(child);
        commentRepository.save(child);
        commentRepository.save(parent);
        commentSystemRepository.save(parent.getSubCommentSystem());
    }
}
