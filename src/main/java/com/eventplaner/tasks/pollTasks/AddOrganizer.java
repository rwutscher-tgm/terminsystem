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
        Configuration config = HibernateUtils.getConfig(new Class[]{
                Poll.class,
                User.class,
                RegisteredUser.class
        });

        SessionFactory factory = null;
        Session session = null;

        factory = config.buildSessionFactory();
        session = factory.openSession();

        this.poll.addOrganizer(this.organizer);

        session.beginTransaction();

        session.save(this.poll);

        session.getTransaction().commit();


        session.close();
        factory.close();
    }
}
