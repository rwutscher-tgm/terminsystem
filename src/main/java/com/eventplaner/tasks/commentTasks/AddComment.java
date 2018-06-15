package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.model.repositories.CommentSystemRepository;
import com.eventplaner.tasks.Task;

/**
 * Fügt einen Kommentar zu einem gegebenen Poll oder Kommentarsystem hinzu
 */
public class AddComment implements Task{

    private CommentSystem system;
    private RegisteredUser author;
    private String comment;
    private CommentRepository commentRepository;
    private CommentSystemRepository commentSystemRepository;

    /**
     * Ein Konstruktor für den AddComment-Task
     * @param system Das Comment-System indem der Kommentar hinzugefügt wird
     * @param comment Der Text des Kommentars
     * @param commentRepository Das Repository indem die Kommentare gespeichert werden
     * @param commentSystemRepository Das Repository indem das Kommantarsystem für die Kommantarantworten gespeichert werden
     */
    public AddComment(CommentSystem system, RegisteredUser author, String comment, CommentRepository commentRepository, CommentSystemRepository commentSystemRepository){
        this.system = system;
        this.author = author;
        this.comment = comment;
        this.commentRepository = commentRepository;
        this.commentSystemRepository = commentSystemRepository;
    }

    /**
     * Ein Konstruktor für den AddComment-Task
     * @param poll Der Poll in dessen Kommentarsystem das Kommentar gespeichert wird
     * @param comment Der Text des Kommentars
     * @param commentRepository Das Repository indem die Kommentare gespeichert werden
     * @param commentSystemRepository Das Repository indem das Kommantarsystem für die Kommantarantworten gespeichert werden
     */
    public AddComment(Poll poll, RegisteredUser author, String comment, CommentRepository commentRepository, CommentSystemRepository commentSystemRepository){
        this.system = poll.getCommentSystem();
        this.author = author;
        this.comment = comment;
        this.commentRepository = commentRepository;
        this.commentSystemRepository = commentSystemRepository;
    }

    /**
     * Führt die Operation aus
     */
    @Override
    public void execute() {
        CommentSystem subComments = new CommentSystem();
        commentSystemRepository.save(subComments);

        commentSystemRepository.flush();

        Comment c = new Comment(this.author, this.comment/*, subComments*/);
        c.setSubCommentSystem(subComments);

        commentRepository.save(c);

        this.system.addComment(c);

        System.out.println("Comment SystemId: "+this.system.getCommentSystemID());
        System.out.println("Comment id: "+c.getCommentID());
        System.out.println("User Id: "+author.getUserID());

        commentSystemRepository.save(system);
    }
}
