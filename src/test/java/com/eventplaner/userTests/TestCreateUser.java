package com.eventplaner.userTests;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.userTasks.CreateUnregisteredUser;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCreateUser extends TestCase {

    @Override
    protected void setUp() throws Exception {
        System.out.println("Setting it up!");
    }

    @Test
    public void testCreateRegisteredUserWithId() {
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw").execute();

        assertEquals(new GetUser("userCreatedWithID").execute().size(), 1);
    }

    @Test
    public void testCreateRegisteredUserWithoutId() {
        new CreateUser("registered2@user.com","regUser2","rootpw").execute();

        assertEquals(new GetUser("registered2@user.com").execute().size(), 1);
    }

    @Test
    public void testCreateUnregisteredUserWithId() {
        new CreateUser("unregUserCreatedWithID","unregistered1@user.com","unRegUser1","rootpw").execute();

        assertEquals(new GetUser("unregUserCreatedWithID").execute().size(), 1);
    }

    @Test
    public void testCreateUnregisteredUserWithoutId() {
        new CreateUser("unregistered2@user.com","unRegUser2","rootpw").execute();

        assertEquals(new GetUser("unregistered2@user.com").execute().size(), 1);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
