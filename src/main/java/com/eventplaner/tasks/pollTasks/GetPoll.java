package com.eventplaner.tasks.pollTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import com.eventplaner.tasks.GetterTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class GetPoll implements GetterTask{

    private String pid;

    public GetPoll(){

    }

    public GetPoll(String pid){
        this.pid = pid;
    }

    @Override
    public ArrayList<Poll> execute() {

        ArrayList<Poll> polls = new ArrayList<>();

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


            if(this.pid != null){
                Query query = session.createQuery("from Poll where id = :i");
                query.setParameter("i", pid);
                polls.addAll(query.list());

            }else{
                Query query = session.createQuery("from Poll");
                polls.addAll(query.list());
            }

            session.getTransaction().commit();

            session.close();
            factory.close();
        }catch(Exception e){
            e.printStackTrace();
        }


        return polls;
    }
}
