package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;

public class RemoveOrganizer implements Task{

    private Poll poll;
    private RegisteredUser organizer;
    private PollRepository pollRepository;

    public RemoveOrganizer(Poll poll, RegisteredUser organizer, PollRepository pollRepository) {
        this.poll = poll;
        this.organizer = organizer;
        this.pollRepository = pollRepository;
    }

    @Override
    public void execute() {
        poll.removeOrganizer(organizer);
        pollRepository.save(poll);
    }
}
