package com.eventplaner;

import com.eventplaner.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class test {

    public static void main(String[] args) {

        Configuration config = HibernateUtils.getConfig(new Class[]{
                /*,
                Poll.class,
                PollTopic.class,*/
                User.class,
                UnregisteredUser.class,
                RegisteredUser.class,
                Comment.class,
                CommentSystem.class
        });

        SessionFactory factory = null;
        Session session = null;

        factory = config.buildSessionFactory();
        session = factory.openSession();

        //Poll p = new Poll("reise nach dort", "wir reisen nach bla", false);

        RegisteredUser u = new RegisteredUser("my@email.com", "testwu", "rootpw");

        Comment c = new Comment(u,"13.05.2018","23:53", "i hate dis");

        Comment c2 = new Comment(u,"14.05.2018","14:05", "does this work");

        CommentSystem p = new CommentSystem();

        p.addComment(c);

        p.addComment(c2);

        session.beginTransaction();

        session.save(u);
        session.save(c);
        session.save(c2);
        session.save(p);

        session.getTransaction().commit();



        session.close();
        factory.close();

    }
}
