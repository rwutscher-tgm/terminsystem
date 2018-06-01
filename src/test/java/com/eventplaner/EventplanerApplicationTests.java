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
    public void testAddComment(){

    }

    @Test
	public void contextLoads() {
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


		// Creating Organizer
		RegisteredUser organizer = new RegisteredUser("organizer@email.com", "organizer", "rootpw");

		// Creating Poll
		Poll poll = new Poll(organizer, "When should we do X?", "We want to know when we should do X!", true);

		// Creating possible dates for event
		PollTopic date1 = new PollTopic("14.3.2019");
		PollTopic date2 = new PollTopic("29.3.2019");

		// Creating Participants
		UnregisteredUser unregisteredParticipant = new UnregisteredUser("dummy@unregistered.user");
		RegisteredUser registeredParticipant = new RegisteredUser("dummy@registered.user", "registered User", "rootpw");

		// Adding possible dates to poll
		poll.addPollTopic(date1);
		poll.addPollTopic(date2);

		// Adding participants to pol
		poll.addParticipant(unregisteredParticipant);
		poll.addParticipant(registeredParticipant);

		// Creating Comment
		Comment comment = new Comment(registeredParticipant, "first");

		// Adding Comment to poll
		poll.getCommentSystem().addComment(comment);


		session.beginTransaction();


		session.save(organizer);
		session.save(poll);
		session.save(date1);
		session.save(date2);
		session.save(unregisteredParticipant);
		session.save(registeredParticipant);
		session.save(comment);


        /*session.save(registeredParticipant);
        session.save(comment);*/

		session.getTransaction().commit();



		session.close();
		factory.close();
	}

}
