package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JoinPoll implements Task{

    private Poll poll;
    private User participant;

    public JoinPoll(Poll poll, User participant) {
        this.poll = poll;
        this.participant = participant;
    }

    @Override
    public void execute() {
        this.poll.addParticipant(this.participant);
        new SaveObject<>(this.participant).execute();
    }
}
