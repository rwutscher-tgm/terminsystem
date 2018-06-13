package com.eventplaner.model.repositories;

import com.eventplaner.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByCommentID(String id);
    ArrayList<Comment> findAllByComment(String comment);

}
