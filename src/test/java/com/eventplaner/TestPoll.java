package com.eventplaner;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.tasks.commentTasks.GetComment;
import com.eventplaner.tasks.pollTasks.*;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.GetUser;
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
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw",registeredUserRepository).execute();
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
        new CreateUser("userCreatedWithID","registered2@user.com","regUser2","rootpw",registeredUserRepository).execute();
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
        new CreateUser("userCreatedWithID","registered3@user.com","regUser3","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer1 = (RegisteredUser) new GetUser("registered3@user.com").execute().get(0);

        new CreateUser("userCreatedWithID","registered@user.com","regUser4","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer2 = (RegisteredUser) new GetUser("registered4@user.com").execute().get(0);

        // Creating Poll
        new CreatePoll(organizer1, "poll_ 2", "poll", true, pollRepository).execute();
        Poll poll = new GetPoll("poll_ 2").execute().get(0);

        // Adding Organizer
        new AddOrganizer(poll, organizer2, pollRepository).execute();
        poll = new GetPoll("poll_ 2").execute().get(0);

        assertEquals(new GetOrganizer(poll).execute().size(), 2);
    }

    @Test
    public void testVoteForTopic() {

    }

    @Test
    public void testRemoveOrganizer(){
        // Creating Organizers
        new CreateUser("userCreatedWithID","registered5@user.com","regUser5","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer1 = (RegisteredUser) new GetUser("registered3@user.com").execute().get(0);

        new CreateUser("userCreatedWithID","registered5@user.com","regUser6","rootpw",registeredUserRepository).execute();
        RegisteredUser organizer2 = (RegisteredUser) new GetUser("registered6@user.com").execute().get(0);

        // Creating Poll
        new CreatePoll(organizer1, "poll_ 3", "poll", true, pollRepository).execute();
        Poll poll = new GetPoll("poll_ 3").execute().get(0);

        // Adding Organizer
        new AddOrganizer(poll, organizer2, pollRepository).execute();
        poll = new GetPoll("poll_ 3").execute().get(0);

        // Removing Organizer
        new RemoveOrganizer(poll, registeredUserRepository.findByEmail("registered6@user.com"), pollRepository).execute();
        poll = new GetPoll("poll_ 3").execute().get(0);


        assertEquals(new GetOrganizer(poll).execute().size(), 0);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteObjects();
    }

    public void deleteObjects(){

        String[] polls = new String[]{
                "poll_ 1",
                "poll_ 2",
                "poll_ 3"
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

                "unregistered1@user.com",
                "unregistered2@user.com"
        };
        for(String user: users){
            try{
                registeredUserRepository.delete(registeredUserRepository.findByEmail(user));
            }catch (Exception e){}
        }

        System.out.println("Cleaning up: ");
        System.out.println("Amount of users in DB: " + new GetUser().execute().size());
        System.out.println("Amount of polls in DB: " + new GetPoll().execute().size());
        System.out.println("Amount of Comments in DB: " + new GetComment().execute().size());

    }

}
