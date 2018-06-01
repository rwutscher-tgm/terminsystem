package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;

public class RemoveVoteForTopic implements Task {

    private Poll poll;

    private User user;

    public RemoveVoteForTopic(Poll poll, User user) {
        this.poll = poll;
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
