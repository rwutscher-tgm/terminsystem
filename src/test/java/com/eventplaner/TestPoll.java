package com.eventplaner;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.commentTasks.GetComment;
import com.eventplaner.tasks.pollTasks.*;
import com.eventplaner.tasks.userTasks.CreateUnregisteredUser;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.DeleteUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

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
    @Test
    public void testCreatePoll() {
        // Creating Organizer
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw").execute();
        RegisteredUser organizer = (RegisteredUser) new GetUser("registered1@user.com").execute().get(0);

        // Creating Poll
        new CreatePoll(organizer, "poll_1", "poll", true);

        assertEquals(new GetPoll("poll_1").execute().get(0).getName(), "poll_1");
    }

    @Test
    public void testaddPollTopic() {
        // Creating Organizer
        new CreateUser("userCreatedWithID","registered2@user.com","regUser2","rootpw").execute();
        RegisteredUser organizer = (RegisteredUser) new GetUser("registered2@user.com").execute().get(0);

        // Creating Poll
        new CreatePoll(organizer, "poll_2", "poll", true);
        Poll poll = new GetPoll("poll_2").execute().get(0);

        // Adding Topic to Poll
        new AddPollTopic(poll, "new topic").execute();
        poll = new GetPoll("poll_2").execute().get(0);

        assertEquals(new GetPollTopics(poll).execute().getDescription(), "new topic");
    }

    @Test
    public void testAddOrganizer(){
        // Creating Organizers
        new CreateUser("userCreatedWithID","registered3@user.com","regUser3","rootpw").execute();
        RegisteredUser organizer1 = (RegisteredUser) new GetUser("registered3@user.com").execute().get(0);

        new CreateUser("userCreatedWithID","registered@user.com","regUser4","rootpw").execute();
        RegisteredUser organizer2 = (RegisteredUser) new GetUser("registered4@user.com").execute().get(0);

        // Creating Poll
        new CreatePoll(organizer1, "poll_ 2", "poll", true).execute();
        Poll poll = new GetPoll("poll_ 2").execute().get(0);

        // Adding Organizer
        new AddOrganizer(poll, organizer2).execute();
        poll = new GetPoll("poll_ 2").execute().get(0);

        assertEquals(new GetOrganizer(poll).execute().size(), 2);
    }

    @Test
    public void testVoteForTopic() {

    }

    @Test
    public void testRemoveOrganizer(){
        // Creating Organizers
        new CreateUser("userCreatedWithID","registered5@user.com","regUser5","rootpw").execute();
        RegisteredUser organizer1 = (RegisteredUser) new GetUser("registered3@user.com").execute().get(0);

        new CreateUser("userCreatedWithID","registered5@user.com","regUser6","rootpw").execute();
        RegisteredUser organizer2 = (RegisteredUser) new GetUser("registered6@user.com").execute().get(0);

        // Creating Poll
        new CreatePoll(organizer1, "poll_ 3", "poll", true).execute();
        Poll poll = new GetPoll("poll_ 3").execute().get(0);

        // Adding Organizer
        new AddOrganizer(poll, organizer2).execute();
        poll = new GetPoll("poll_ 3").execute().get(0);

        // Removing Organizer
        new RemoveOrganizer(poll, (RegisteredUser) new GetUser("registered6@user.com").execute().get(0)).execute();
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
                new DeleteObject<>(new GetPoll(poll).execute().get(0)).execute();
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
                new DeleteObject<>(new GetUser(user).execute().get(0)).execute();
            }catch (Exception e){}
        }

        System.out.println("Cleaning up: ");
        System.out.println("Amount of users in DB: " + new GetUser().execute().size());
        System.out.println("Amount of polls in DB: " + new GetPoll().execute().size());
        System.out.println("Amount of Comments in DB: " + new GetComment().execute().size());

    }

}
