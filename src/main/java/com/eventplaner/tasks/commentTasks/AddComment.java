package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.CommentRepository;
import com.eventplaner.model.repositories.CommentSystemRepository;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;

public class AddComment implements Task{

    private CommentSystem system;
    private RegisteredUser author;
    private String comment;
    private CommentRepository commentRepository;
    private CommentSystemRepository commentSystemRepository;

    public AddComment(CommentSystem system, RegisteredUser author, String comment, CommentRepository commentRepository, CommentSystemRepository commentSystemRepository){
        this.system = system;
        this.author = author;
        this.comment = comment;
        this.commentRepository = commentRepository;
        this.commentSystemRepository = commentSystemRepository;
    }

    public AddComment(Poll poll, RegisteredUser author, String comment, CommentRepository commentRepository, CommentSystemRepository commentSystemRepository){
        this.system = poll.getCommentSystem();
        this.author = author;
        this.comment = comment;
        this.commentRepository = commentRepository;
        this.commentSystemRepository = commentSystemRepository;
    }

    @Override
    public void execute() {
        Comment c = new Comment(this.author, this.comment);
        this.system.addComment(c);
        commentRepository.save(c);
        commentSystemRepository.save(system);
    }
}
