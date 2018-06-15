package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.model.repositories.CommentSystemRepository;
import com.eventplaner.tasks.Task;

/**
 * Ändert den Text eines Kommentars
 */
public class EditComment implements Task{

    private Comment comment;
    private String newText;
    private CommentRepository commentRepository;

    /**
     * Der Konstruktor für den EditComment Task
     * @param comment Das Kommentar das editiert werden soll
     * @param newText Der neue Text des Kommentars
     * @param commentRepository Das Repository indem das Kommentar gespeichert werden soll
     */
    public EditComment(Comment comment, String newText, CommentRepository commentRepository) {
        this.comment = comment;
        this.newText = newText;
        this.commentRepository = commentRepository;
    }

    /**
     * Führt die Operation aus
     */
    @Override
    public void execute() {
        comment.setComment(newText);
        commentRepository.save(comment);
    }
}
