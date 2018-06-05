package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddPollTopic implements Task{

    private Poll poll;
    private String description;

    public AddPollTopic(Poll poll, String description) {
        this.poll = poll;
        this.description = description;
    }

    @Override
    public void execute() {
        PollTopic topic = new PollTopic(this.description);
        new SaveObject<>(topic).execute();
        this.poll.addPollTopic(topic);
    }
}
