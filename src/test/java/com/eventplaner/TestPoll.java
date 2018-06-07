package com.eventplaner;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.tasks.pollTasks.*;
import com.eventplaner.tasks.userTasks.CreateUser;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebIntegrationTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DataJpaTest
public class TestPoll extends TestCase{

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Setting up: ");
        deleteObjects();
    }

    /*
        Create Poll Test
     */

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollTopicRepository pollTopicRepository;

    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Test
    public void testCreatePoll() {
        // Creating Organizer
        new CreateUser("registered1@user.com","regUser1","rootpw",registeredUserRepository).execute();
//        RegisteredUser organizer = (RegisteredUser) new GetUser("registered1@user.com").execute().get(0);
        RegisteredUser organizer = registeredUserRepository.findByEmail("registered1@user.com");
        System.out.println(organizer.getUsername());

//        RegisteredUser user = new RegisteredUser("rwutscher@student.tgm.ac.at", "rootpw", "Richard Wutscher");
//        registeredUserRepository.save(user);
//        Poll poll = new Poll(registeredUserRepository.findByEmail("rwutscher@student.tgm.ac.at"), "TestPoll", "this poll is only for test purposes", false);
//        pollRepository.save(poll);
//        System.out.println(pollRepository.findByName("TestPoll").getName());

        // Creating Poll
        new CreatePoll(organizer, "poll_1", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_1").get(0);

        assertEquals("poll_1", pollRepository.findAllByName("poll_1").get(0).getName());
        pollRepository.delete(poll);
        System.out.println("deleted");
    }

    @Test
    public void testaddPollTopic() {
        // Creating Organizer
        new CreateUser("registered2@user.com","regUser2","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer = registeredUserRepository.findByEmail("registered1@user.com");

        // Creating Poll
        new CreatePoll(organizer, "poll_2", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_2").get(0);

        // Adding Topic to Poll
        new AddPollTopic(poll, "new topic", pollRepository, pollTopicRepository).execute();
        poll = pollRepository.findAllByName("poll_2").get(0);

        assertEquals("new topic", poll.getPollTopics().get(0).getDescription());
    }

    @Test
    public void testAddOrganizer(){
        // Creating Organizers
        new CreateUser("registered3@user.com","regUser3","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer1 = registeredUserRepository.findByEmail("registered3@user.com");

        new CreateUser("registered@user.com","regUser4","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer2 = registeredUserRepository.findByEmail("registered@user.com");

        // Creating Poll
        new CreatePoll(organizer1, "poll_2", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_2").get(0);

        // Adding Organizer
        new AddOrganizer(poll, organizer2, pollRepository).execute();
        poll = pollRepository.findAllByName("poll_2").get(0);

        assertEquals(2, poll.getOrganizers().size());
    }

    @Test
    public void testVoteForTopic() {
        new CreateUser("registered20@user.com","regUser2","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer = registeredUserRepository.findByEmail("registered20@user.com");

        new CreatePoll(organizer, "MyName", "MyDescription", true, pollRepository).execute();
        PollTopic pollTopic = new PollTopic("MyPoll");
        pollTopicRepository.save(pollTopic);
        Poll poll = pollRepository.findAllByName("MyName").get(0);
        poll.addPollTopic(pollTopicRepository.findByDescription("MyPoll"));
        new VoteForTopic(organizer, pollTopicRepository.findByDescription("MyPoll"), pollTopicRepository).execute();
        assertEquals(pollRepository.findAllByName("MyName").get(0).getPollTopics().get(0).getAvailables().size(), 1);
    }

    @Test
    public void testRemoveOrganizer(){
        // Creating Organizers
        new CreateUser("registered5@user.com","regUser5","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer1 = registeredUserRepository.findByEmail("registered5@user.com");

        new CreateUser("registered6@user.com","regUser6","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer2 = registeredUserRepository.findByEmail("registered6@user.com");

        // Creating Poll
        new CreatePoll(organizer1, "poll_3", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_3").get(0);

        // Adding Organizer
        new AddOrganizer(poll, organizer2, pollRepository).execute();
        poll = pollRepository.findAllByName("poll_3").get(0);

        // Removing Organizer
        new RemoveOrganizer(poll, registeredUserRepository.findByEmail("registered6@user.com"), pollRepository).execute();
        poll = pollRepository.findAllByName("poll_3").get(0);


        assertEquals(1, pollRepository.findAllByName("poll_3").get(0).getOrganizers().size());
    }

    @Test
    public void testFinalizePoll(){
        new CreateUser("registered7@user.com","regUser7","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer1 = registeredUserRepository.findByEmail("registered7@user.com");

        new CreatePoll(organizer1, "poll_4", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_4").get(0);

        poll.addPollTopic(new PollTopic("test"));

        new FinalizePoll(poll, true, pollRepository).execute();

        assertEquals(pollRepository.findAllByName("poll_4").size(), 0);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteObjects();
    }

    public void deleteObjects(){

        String[] polls = new String[]{
                "poll_1",
                "poll_2",
                "poll_3",
                "poll_4",
                "MyName"
        };
        for(String poll: polls){
            try{
                pollRepository.delete(pollRepository.findAllByName(poll).get(0));
            }catch (Exception e){}
        }


        String[] users = new String[]{
                "registered1@user.com",
                "registered2@user.com",
                "registered3@user.com",
                "registered4@user.com",
                "registered5@user.com",
                "registered6@user.com",
                "registered7@user.com",
                "registered20@user.com",

                "unregistered1@user.com",
                "unregistered2@user.com"
        };
        for(String user: users){
            try{
                registeredUserRepository.delete(registeredUserRepository.findByEmail(user));
            }catch (Exception e){}
        }

    }

}
