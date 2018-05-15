package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.Task;

public class AddOrganizer implements Task{

    private Poll poll;
    private RegisteredUser organizer;

    public AddOrganizer(Poll poll, RegisteredUser organizer) {
        this.poll = poll;
        this.organizer = organizer;
    }

    @Override
    public void execute() {

    }
}
