package com.eventplaner.tasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SaveObject<T> implements Task {

    public T object;

    public SaveObject(T object){
        this.object = object;
    }

    @Override
    public void execute() {

        // Adding all model classes to hibernate config
        Configuration config = HibernateUtils.getConfig(new Class[]{
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

                session.beginTransaction();

                session.save(object);

                session.getTransaction().commit();

            session.close();
            factory.close();
        }catch(Exception e){
                try{
                    factory = config.buildSessionFactory();
                    session = factory.openSession();

                    session.beginTransaction();

                    session.update(object);

                    session.getTransaction().commit();

                    session.close();
                    factory.close();
                }catch (Exception e2){
                    e2.printStackTrace();
                }


        }

    }
}
