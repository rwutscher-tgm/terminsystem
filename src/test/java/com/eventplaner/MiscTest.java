package com.eventplaner;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.DeleteUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MiscTest extends TestCase{

    @Override
    protected void setUp() throws Exception{

    }


    @Test
    public void testdirtyTest() {

        RegisteredUser registeredParticipant = new RegisteredUser("dummy@registered.user", "registered User", "rootpw");

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


        new DeleteUser((RegisteredUser) new GetUser("dummy@registered.user").execute().get(0)).execute();


        assertEquals(0, new GetUser().execute().size());
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
