package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import com.eventplaner.tasks.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreatePoll implements Task{

    private Poll poll;

    public CreatePoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {
        Configuration config = HibernateUtils.getConfig(new Class[]{
                Poll.class
        });

        SessionFactory factory = null;
        Session session = null;

        factory = config.buildSessionFactory();
        session = factory.openSession();


        session.beginTransaction();

            session.save(this.poll);

        session.getTransaction().commit();


        session.close();
        factory.close();
    }
}
