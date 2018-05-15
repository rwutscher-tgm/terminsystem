package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class LeavePoll implements Task{

    private Poll poll;

    public LeavePoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {

    }
}
