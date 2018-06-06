package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;
import org.springframework.stereotype.Repository;

public class VoteForTopic implements Task{

    private User user;

    private Repository repository;

    private PollTopic polltopic;

    public VoteForTopic(User user, Repository repository, PollTopic polltopic) {
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
