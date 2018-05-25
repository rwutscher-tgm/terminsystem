package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddPollTopic implements Task{

    private Poll poll;
    private PollTopic topic;

    public AddPollTopic(Poll poll, PollTopic topic) {
        this.poll = poll;
        this.topic = topic;
    }

    @Override
    public void execute() {
        Configuration config = HibernateUtils.getConfig(new Class[]{
                Poll.class,
                PollTopic.class
        });

        SessionFactory factory = null;
        Session session = null;

        factory = config.buildSessionFactory();
        session = factory.openSession();

        this.poll.addPollTopic(this.topic);

        session.beginTransaction();

        session.save(this.poll);

        session.getTransaction().commit();


        session.close();
        factory.close();
    }
}
