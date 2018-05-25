package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LeavePoll implements Task{

    private Poll poll;
    private User participant;

    public LeavePoll(Poll poll, User participant) {
        this.poll = poll;
        this.participant = participant;
    }

    @Override
    public void execute() {
        Configuration config = HibernateUtils.getConfig(new Class[]{
                Poll.class,
                User.class
        });

        SessionFactory factory = null;
        Session session = null;

        factory = config.buildSessionFactory();
        session = factory.openSession();

        this.poll.removeParticipant(this.participant);

        session.beginTransaction();

        session.save(this.poll);

        session.getTransaction().commit();


        session.close();
        factory.close();
    }
}
