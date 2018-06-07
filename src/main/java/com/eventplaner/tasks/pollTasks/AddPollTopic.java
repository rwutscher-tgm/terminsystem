package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
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

        /*Configuration config = HibernateUtils.getConfig(new Class[]{
                Poll.class,
                PollTopic.class,
                User.class,
                UnregisteredUser.class,
                RegisteredUser.class,
                Comment.class,
                CommentSystem.class
        });

        SessionFactory factory = null;
        Session session = null;

        try{
            factory = config.buildSessionFactory();
            session = factory.openSession();

            session.beginTransaction();*/

            PollTopic topic = new PollTopic(this.description);
            new SaveObject<>(topic).execute();

            this.poll.addPollTopic(topic);
            new SaveObject<>(this.poll).execute();
/*
            session.getTransaction().commit();

            session.close();
            factory.close();
        }catch(Exception e){
            e.printStackTrace();
        }
*/
    }
}
