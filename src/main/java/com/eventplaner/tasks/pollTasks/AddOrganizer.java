package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;

/**
 * Fügt einen Organisator zu einem Poll hinzu
 */
public class AddOrganizer implements Task{

    private Poll poll;
    private RegisteredUser organizer;
    private PollRepository pollRepository;

    /**
     * Der Konstruktor für den AddOrganizer Task
     * @param poll Der Poll zu dem ein Organisator hinzugefügt werden soll
     * @param organizer Der User der als Organisator hinzugefügt werden sol
     * @param pollRepository Das Repository indem der Poll gespeichert werden soll
     */
    public AddOrganizer(Poll poll, RegisteredUser organizer, PollRepository pollRepository) {
        this.poll = poll;
        this.organizer = organizer;
        this.pollRepository = pollRepository;
    }

    /**
     * Führt die Operation aus
     */
    @Override
    public void execute() {
        this.poll.addOrganizer(this.organizer);
        new JoinPoll(this.poll, organizer, pollRepository).execute();
        pollRepository.save(this.poll);
    }
}
