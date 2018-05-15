package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;

public class VoteForTopic implements Task{

    private Poll poll;

    private User user;

    public VoteForTopic(Poll poll, User user) {
        this.poll = poll;
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
