package com.eventplaner;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.pollTasks.AddPollTopic;
import com.eventplaner.tasks.pollTasks.CreatePoll;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.pollTasks.GetPollTopics;
import com.eventplaner.tasks.userTasks.CreateUnregisteredUser;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.DeleteUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

public class TestPoll extends TestCase{
    @Override
    protected void setUp() throws Exception {
        System.out.println("Setting it up!");
        deleteObjects();

        System.out.println("Set up finished!");
    }

    /*
        Create Poll Test
     */
    @Test
    public void testCreatePoll() {
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw").execute();
        new CreatePoll((RegisteredUser) new GetUser("registered1@user.com").execute().get(0), "poll_1", "poll", true);

        assertEquals(new GetPoll("registered1@user.com").execute().get(0).getName(), "poll_1");
    }

    @Test
    public void addPollTopic() {
        new CreateUser("userCreatedWithID","registered2@user.com","regUser2","rootpw").execute();
        new GetUser("registered2@user.com").execute().get(0);
        Poll poll = new GetPoll("registered1@user.com").execute().get(0);
        new AddPollTopic(poll, "new topic").execute();

        assertEquals(new GetPollTopics(new GetPoll("registered1@user.com").execute().get(0)).execute().getDescription(), "new topic");
    }

    public void deleteObjects(){
        String[] users = new String[]{
                "registered1@user.com",
                "registered2@user.com",
                "registered3@user.com",
                "registered4@user.com",
                "registered5@user.com",
                "registered6@user.com",

                "unregistered1@user.com",
                "unregistered2@user.com",
        };
        for(String user: users){
            try{
                new DeleteObject<>(new GetUser(user).execute().get(0)).execute();
            }catch (Exception e){}
        }

    }

}
