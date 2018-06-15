package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;

/**
 * Entfernt einen Partizipant von einem Poll
 */
public class LeavePoll implements Task{

    private Poll poll;
    private User participant;
    private PollRepository pollRepository;

    /**
     * Der Konstruktor des LeavePoll Tasks
     * @param poll Der Poll aus dem ein Partizipant entfernt werden soll.
     * @param participant Der Benutzer der entfernt wird
     * @param pollRepository Das Repository indem der Poll gespeichert wird
     */
    public LeavePoll(Poll poll, User participant, PollRepository pollRepository) {
        this.poll = poll;
        this.participant = participant;
        this.pollRepository = pollRepository;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        this.poll.removeParticipant(this.participant);
        pollRepository.save(this.poll);
    }
}
