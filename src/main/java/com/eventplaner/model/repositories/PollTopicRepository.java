package com.eventplaner.model.repositories;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollTopicRepository extends JpaRepository<PollTopic ,Long>{
    PollTopic findById(String id);
    PollTopic findByDescription(String description);
}
