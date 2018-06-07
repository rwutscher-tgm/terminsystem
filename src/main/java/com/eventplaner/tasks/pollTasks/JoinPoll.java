package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;

public class JoinPoll implements Task{

    private Poll poll;
    private User participant;
    private PollRepository pollRepository;


    public JoinPoll(Poll poll, User participant) {
        this.poll = poll;
        this.participant = participant;
    }

    public JoinPoll(Poll poll, User participant, PollRepository pollRepository) {
        this.poll = poll;
        this.participant = participant;
        this.pollRepository = pollRepository;
    }

    @Override
    public void execute() {
        this.poll.addParticipant(this.participant);
        pollRepository.save(poll);
        //new SaveObject(this.poll).execute();
        System.out.println("Joined\n\n\n\n\n ....................................................................................");
    }
}
