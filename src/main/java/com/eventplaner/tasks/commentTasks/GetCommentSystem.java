package com.eventplaner.tasks.commentTasks;

import com.eventplaner.HibernateUtils;
import com.eventplaner.model.*;
import com.eventplaner.tasks.GetterTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GetCommentSystem implements GetterTask{
    private User user;
    private CommentSystem system;
    private String cid;

    public GetCommentSystem(){

    }
    public GetCommentSystem(String cid) {
        this.cid = cid;
    }

    @Override
    public ArrayList<CommentSystem> execute() {




        ArrayList<CommentSystem> polls = new ArrayList<>();

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

            if(this.cid != null) {
                Query query = session.createQuery("from CommentSystem where comment_system_id = :i");
                query.setParameter("i", this.cid);
                polls.addAll(query.list());

            }else{
                Query query = session.createQuery("from CommentSystem");
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
