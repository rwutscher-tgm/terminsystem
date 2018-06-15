package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.*;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

/**
 * Erstellt einen neuen Poll
 */
public class CreatePoll implements Task{

    private RegisteredUser organizer;
    private String name;
    private String description;
    private boolean isPublic;
    private String id;
    private PollRepository pollRepository;

    /**
     * Der Konstruktor für den CreatePoll Task
     * @param organizer Der Ersteller des Polls
     * @param name Der Name des Polls
     * @param description Die Beschreibung des Polls
     * @param isPublic Ob der Poll public oder private ist
     * @param pollRepository Das Repository indem der Poll gespeichert werden soll
     */
    public CreatePoll(RegisteredUser organizer, String name, String description, boolean isPublic, PollRepository pollRepository) {
        this.organizer = organizer;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.pollRepository = pollRepository;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        Poll poll = new Poll(this.organizer, name, description, isPublic);
        this.id = poll.getId();
        pollRepository.save(poll);
    }

    public String getId(){
        return this.id;
    }
}
