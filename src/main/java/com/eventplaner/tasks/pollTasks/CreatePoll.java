package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class CreatePoll implements Task{

    private Poll poll;

    public CreatePoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {

    }
}
