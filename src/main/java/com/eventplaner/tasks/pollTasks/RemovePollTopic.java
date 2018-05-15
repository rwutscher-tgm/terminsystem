package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.tasks.Task;

public class RemovePollTopic implements Task{

    private Poll poll;

    private PollTopic pollTopic;

    public RemovePollTopic(Poll poll, PollTopic pollTopic) {
        this.poll = poll;
        this.pollTopic = pollTopic;
    }

    @Override
    public void execute() {

    }
}
