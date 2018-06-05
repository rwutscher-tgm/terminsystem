package com.eventplaner.tasks.userTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.GetterTask;
import com.eventplaner.tasks.pollTasks.GetPoll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
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

    /*@Autowired
    private UserRepository userRepository;*/

    @Override
    public ArrayList<User> execute() {

        /*System.out.println(userRepository);

        if(this.uid != null){
            return new ArrayList<>(Arrays.asList(userRepository.findByUserID(uid)));
        }else if(this.email != null){
            return new ArrayList<>(Arrays.asList(userRepository.findByEmail(email)));
        }else if(this.poll != null){
            return (ArrayList<User>) poll.getParticipants();
        }else{
            for(User user: userRepository.findAll()){
                System.out.println(user.getEmail());
            }
            return new ArrayList<>(userRepository.findAll());
        }*/





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
            }else if(this.email != null){
                Query query = session.createQuery("from User where email = :i");
                query.setParameter("i", email);
                polls.addAll(query.getResultList());
            }else if(this.poll != null){
                return (ArrayList<User>) poll.getParticipants();
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
