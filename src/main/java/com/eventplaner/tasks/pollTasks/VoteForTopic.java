package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;

public class VoteForTopic implements Task{

    private Poll poll;

    private User user;

    private PollTopic topic;

    public VoteForTopic(Poll poll, User user, PollTopic topic) {
        this.poll = poll;
        this.user = user;
        this.topic = topic;
    }

    @Override
    public void execute() {

    }
}
