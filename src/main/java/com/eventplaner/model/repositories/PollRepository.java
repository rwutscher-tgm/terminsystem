package com.eventplaner.model.repositories;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository  extends CrudRepository<Poll, Long> {
    Poll findById(String id);
}
