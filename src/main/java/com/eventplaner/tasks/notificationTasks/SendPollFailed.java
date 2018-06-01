package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class SendPollFailed implements Task {

    private Poll poll;

    public SendPollFailed(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {

    }
}
