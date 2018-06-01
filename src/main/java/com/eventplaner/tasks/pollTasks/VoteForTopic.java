package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;

public class VoteForTopic implements Task{

    private Poll poll;
    private String topic;
    private User user;

    public VoteForTopic(Poll poll, User user, String topic) {
        this.poll = poll;
        this.user = user;
        this.topic = topic;
    }

    @Override
    public void execute() {
        this.poll.addPollTopic();
    }
}
