package com.eventplaner.model.repositories;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentSystemRepository extends JpaRepository<CommentSystem, Long> {
    CommentSystem findByCommentSystemID(String id);
}
