package com.eventplaner;

import com.eventplaner.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import junit.framework.TestCase;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventplanerApplicationTests extends TestCase {

    static Logger log = Logger.getLogger(EventplanerApplicationTests.class.getName());
    private SessionFactory factory;


    @Override
    protected void setUp() throws Exception {
        log.info("Setting up unittests!");
    }

    @Test
	public void dirtyTest() {
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

		factory = config.buildSessionFactory();
		session = factory.openSession();

		RegisteredUser registeredParticipant = new RegisteredUser("dummy@registered.user", "registered User", "rootpw");

		session.beginTransaction();

		session.save(registeredParticipant);

        /*session.save(registeredParticipant);
        session.save(comment);*/

		session.getTransaction().commit();

		session.close();
		factory.close();
	}

}
