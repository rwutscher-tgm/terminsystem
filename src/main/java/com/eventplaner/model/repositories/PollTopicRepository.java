package com.eventplaner.model.repositories;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PollTopicRepository extends JpaRepository<PollTopic ,Long>{
    PollTopic findById(String id);

}
