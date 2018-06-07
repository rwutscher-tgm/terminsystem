package com.eventplaner.model.repositories;

import com.eventplaner.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Poll findById(String id);
    List<Poll> findAllByName(String name);
}
