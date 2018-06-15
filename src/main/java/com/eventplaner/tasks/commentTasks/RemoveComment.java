package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.tasks.Task;

/**
 * Löscht einen Kommentar
 */
public class RemoveComment implements Task{

    private Comment comment;
    private CommentRepository commentRepository;

    /**
     * Der Konstruktor für den RemoveComment Task
     * @param comment Der Kommentar der gelöscht werden soll
     * @param commentRepository Das Repository aus dem das Kommentar gelöscht werden soll
     */
    public RemoveComment(Comment comment, CommentRepository commentRepository) {
        this.comment = comment;
        this.commentRepository = commentRepository;
    }

    /**
     * Führt die Operation aus
     */
    @Override
    public void execute() {
        commentRepository.delete(comment);
//        new DeleteObject<>(this.comment);
    }
}