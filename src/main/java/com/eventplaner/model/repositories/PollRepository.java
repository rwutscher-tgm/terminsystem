package com.eventplaner.model.repositories;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PollRepository  extends CrudRepository<Poll, Long> {
    Poll findById(String id);
    List<Poll> findAllByName(String name);
}
