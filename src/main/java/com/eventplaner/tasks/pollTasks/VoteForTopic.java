package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.tasks.Task;

public class VoteForTopic implements Task{

    private User user;

    private PollTopicRepository repository;

    private PollTopic polltopic;

    public VoteForTopic(User user, PollTopic polltopic, PollTopicRepository repository) {
        this.user = user;
        this.repository = repository;
        this.polltopic = polltopic;
    }

    @Override
    public void execute() {
        this.polltopic.addAvailable(user);
        repository.save(this.polltopic);
    }
}