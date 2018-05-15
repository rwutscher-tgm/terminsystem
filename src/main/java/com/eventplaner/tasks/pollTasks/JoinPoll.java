package com.eventplaner.tasks.pollTasks;

import com.eventplaner.tasks.Task;

public class JoinPoll implements Task{

    private Task task;

    public JoinPoll(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {

    }
}
