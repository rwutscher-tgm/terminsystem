package com.eventplaner;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

public class Testtest extends TestCase{

    @Override
    protected void setUp() throws Exception{

    }


    @Test
    public void testdirtyTest() {
        RegisteredUser registeredParticipant = new RegisteredUser("idd","dummy@registered.user", "registered User", "rootpw");

        System.out.println(new GetUser(registeredParticipant.getUserID()).execute().size());

        new SaveObject(registeredParticipant).execute();

        System.out.println(new GetUser(registeredParticipant.getUserID()).execute().size());

        new DeleteObject(registeredParticipant).execute();

        System.out.println(new GetUser(registeredParticipant.getUserID()).execute().size());

        assertEquals(new GetUser(registeredParticipant.getUserID()).execute().size(), 0);
    }

    @Test
    public void testdirtyTest2() {
        new CreateUser("idd","dummy@registered.user", "registered User", "rootpw").execute();

        System.out.println(new GetUser().execute().get(0).getUserID());

        new DeleteObject(new GetUser("idd").execute().get(0)).execute();

        System.out.println(new GetUser().execute().size());

        assertEquals(new GetUser().execute().size(), 0);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
