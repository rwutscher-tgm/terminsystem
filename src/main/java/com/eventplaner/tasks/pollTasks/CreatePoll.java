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

public class CreatePoll implements Task{

    private RegisteredUser organizer;
    private String name;
    private String description;
    private boolean isPublic;
    private String id;
    private PollRepository pollRepository;

    public CreatePoll(RegisteredUser organizer, String name, String description, boolean isPublic, PollRepository pollRepository) {
        this.organizer = organizer;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.pollRepository = pollRepository;
    }

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
