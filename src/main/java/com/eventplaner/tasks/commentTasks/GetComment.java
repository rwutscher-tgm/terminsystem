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

public class GetComment implements GetterTask{

    private User user;
    private CommentSystem system;
    private String cid;

    public GetComment(){

    }

    public GetComment(User user) {
        this.user = user;
    }

    public GetComment(CommentSystem system) {
        this.system = system;
    }

    public GetComment(String cid) {
        this.cid = cid;
    }

    @Override
    public ArrayList<Comment> execute() {




        ArrayList<Comment> polls = new ArrayList<>();

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


            if(this.user != null){
                //TODO: Implement get all Comments from a User
            }else if(this.system != null){
                Query query = session.createQuery("from stored_comment where CommentSystem_comment_system_id = :i");
                query.setParameter("i", system.getCommentSystemID());
                List<String> commentIds = query.list();

                for(String commentId: commentIds){
                    polls.add(new GetComment(commentId).execute().get(0));
                }
            }else if(this.cid != null) {
                Query query = session.createQuery("from Comment where comment_id = :i");
                query.setParameter("i", this.cid);
                polls.addAll(query.list());

            }else{
                Query query = session.createQuery("from Comment");
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
