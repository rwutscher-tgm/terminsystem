package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.tasks.Task;
import org.springframework.stereotype.Repository;

public class RemoveVoteForTopic implements Task {

    private User user;

    private PollTopicRepository repository;

    private PollTopic polltopic;

    public RemoveVoteForTopic(User user, PollTopicRepository repository, PollTopic polltopic) {
        this.user = user;
        this.repository = repository;
        this.polltopic = polltopic;
    }

    @Override
    public void execute() {
        this.polltopic.removeAvailable(user);
        repository.save(this.polltopic);
    }
}
