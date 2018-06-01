package com.eventplaner.tasks.userTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import com.eventplaner.tasks.GetterTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

public class GetUser implements GetterTask {

    private String uid;
    private String email;
    private Poll poll;

    public GetUser(){

    }

    public GetUser(Poll poll){
        this.poll = poll;
    }

    public GetUser(String string){
        if(isEmail(string)){
            this.email = string;
        }else{
            this.uid = string;
        }
    }

    public boolean isEmail(String string) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (string == null)
            return false;
        return pat.matcher(string).matches();
    }

    @Override
    public ArrayList<User> execute() {
        ArrayList<User> polls = new ArrayList<>();

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



            if(this.uid != null){
                Query query = session.createQuery("from User where id = :i");
                query.setParameter("i", uid);
                polls.addAll(query.getResultList());

                System.out.println("uid");
            }else if(this.email != null){
                Query query = session.createQuery("from User where email = :i");
                query.setParameter("i", email);
                polls.addAll(query.getResultList());

                System.out.println("uid");
            }else if(this.poll != null){
                //TODO: Implement get all Users in a Poll
            }else{
                Query query = session.createQuery("from User");
                polls.addAll(query.getResultList());
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
