package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;

public class AddOrganizer implements Task{

    private Poll poll;
    private RegisteredUser organizer;
    private PollRepository pollRepository;

    public AddOrganizer(Poll poll, RegisteredUser organizer, PollRepository pollRepository) {
        this.poll = poll;
        this.organizer = organizer;
        this.pollRepository = pollRepository;
    }

    @Override
    public void execute() {
        this.poll.addOrganizer(this.organizer);
        new JoinPoll(this.poll, organizer, pollRepository).execute();
        pollRepository.save(this.poll);
    }
}
