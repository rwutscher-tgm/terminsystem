package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddOrganizer implements Task{

    private Poll poll;
    private RegisteredUser organizer;

    public AddOrganizer(Poll poll, RegisteredUser organizer) {
        this.poll = poll;
        this.organizer = organizer;
    }

    @Override
    public void execute() {
        SaveObject<Poll>(poll).execute();
        SaveObject<RegisteredUser>(organizer).execute();
    }
}
