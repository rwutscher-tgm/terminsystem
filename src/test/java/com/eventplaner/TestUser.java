package com.eventplaner;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.userTasks.CreateUnregisteredUser;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.DeleteUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

public class TestUser extends TestCase {

    @Override
    protected void setUp() throws Exception {
        System.out.println("Setting it up!");
        try{
            new DeleteUser(new GetUser("registered1@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("registered2@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("unregistered1@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("unregistered2@user.com").execute().get(0)).execute();
        }catch(Exception e){}
        System.out.println("Setting up!");
    }

    /*
        Create User Tests
     */
    @Test
    public void testCreateRegisteredUserWithId() {
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw").execute();

        assertEquals("userCreatedWithID", new GetUser("registered1@user.com").execute().get(0).getUserID());
    }

    @Test
    public void testCreateRegisteredUserWithoutId() {
        new CreateUser("registered2@user.com","regUser2","rootpw").execute();

        assertEquals(1, new GetUser("registered2@user.com").execute().size());
    }

    @Test
    public void testCreateUnregisteredUserWithId() {
        new CreateUser("unregUserCreatedWithID","unregistered1@user.com","unRegUser1","rootpw").execute();

        assertEquals("unregUserCreatedWithID", new GetUser("unregistered1@user.com").execute().get(0).getUserID());
    }

    @Test
    public void testCreateUnregisteredUserWithoutId() {
        new CreateUser("unregistered2@user.com","unRegUser2","rootpw").execute();

        assertEquals(1, new GetUser("unregistered2@user.com").execute().size());
    }

    /*
        Login Tests
     */



    /*
        Password Tests
     */

    @Test
    public void testChangePassword(){

    }

    @Override
    protected void tearDown() throws Exception {
        new DeleteUser(new GetUser("registered1@user.com").execute().get(0)).execute();
        new DeleteUser(new GetUser("registered2@user.com").execute().get(0)).execute();
        new DeleteUser(new GetUser("unregistered1@user.com").execute().get(0)).execute();
        new DeleteUser(new GetUser("unregistered2@user.com").execute().get(0)).execute();
    }
}
