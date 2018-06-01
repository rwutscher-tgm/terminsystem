package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreatePoll implements Task{

    private RegisteredUser organizer;
    private String name;
    private String description;
    private boolean isPublic;

    public CreatePoll(RegisteredUser organizer, String name, String description, boolean isPublic) {
        this.organizer = organizer;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    @Override
    public void execute() {
        new SaveObject<>(new Poll(this.organizer, name, description, isPublic)).execute();
    }
}
