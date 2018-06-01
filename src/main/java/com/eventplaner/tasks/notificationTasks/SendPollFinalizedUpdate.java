package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class SendPollFinalizedUpdate implements Task{

    private Poll poll;

    public SendPollFinalizedUpdate(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {

    }
}
