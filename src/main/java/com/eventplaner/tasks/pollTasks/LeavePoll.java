package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;

public class LeavePoll implements Task{

    private Poll poll;
    private User participant;
    private PollRepository pollRepository;

    public LeavePoll(Poll poll, User participant, PollRepository pollRepository) {
        this.poll = poll;
        this.participant = participant;
        this.pollRepository = pollRepository;
    }

    @Override
    public void execute() {
        this.poll.removeParticipant(this.participant);
        pollRepository.save(this.poll);
    }
}
