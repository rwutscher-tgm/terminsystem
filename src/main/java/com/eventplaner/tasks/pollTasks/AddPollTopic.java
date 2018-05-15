package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.tasks.Task;

public class AddPollTopic implements Task{

    private Poll poll;
    private PollTopic topic;

    public AddPollTopic(Poll poll, PollTopic topic) {
        this.poll = poll;
        this.topic = topic;
    }

    @Override
    public void execute() {

    }
}
