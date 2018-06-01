package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class FinalizePoll implements Task{

    private Poll poll;

    public FinalizePoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {

    }
}
