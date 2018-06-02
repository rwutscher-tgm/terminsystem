package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.tasks.GetterTask;
import com.eventplaner.tasks.Task;

public class GetPollTopics implements GetterTask {

    private Poll poll;

    public GetPollTopics(Poll poll) {
        this.poll = poll;
    }

    @Override
    public PollTopic execute() {
        return null;
    }
}
