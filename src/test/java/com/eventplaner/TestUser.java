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
        deleteUsers();

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
        new CreateUnregisteredUser("unregUserCreatedWithID", "unregistered1@user.com").execute();

        assertEquals("unregUserCreatedWithID", new GetUser("unregistered1@user.com").execute().get(0).getUserID());
    }

    @Test
    public void testCreateUnregisteredUserWithoutId() {
        new CreateUnregisteredUser("unregistered2@user.com").execute();

        assertEquals(1, new GetUser().execute().size());
    }

    /*
        Login Tests
     */



    /*
        Password Tests
     */

    @Test
    public void testIsPasswordRight(){
        new CreateUser("registered4@user.com","regUser4","rootpw").execute();
        RegisteredUser user = (RegisteredUser) new GetUser("registered4@user.com").execute().get(0);

        assertTrue(user.isPassword("rootpw"));
    }

    @Test
    public void testIsPasswordWrong(){
        new CreateUser("registered5@user.com","regUser5","rootpw").execute();
        RegisteredUser user = (RegisteredUser) new GetUser("registered5@user.com").execute().get(0);

        assertFalse(user.isPassword("RootPw"));
    }

    @Test
    public void testChangePasswordRight(){
        new CreateUser("registered6@user.com","regUser6","rootpw").execute();
        RegisteredUser user = (RegisteredUser) new GetUser("registered6@user.com").execute().get(0);
        user.setPassword("newPw");

        assertTrue(user.isPassword("newPw"));
    }

    @Test
    public void testChangePasswordWrong(){
        new CreateUser("registered7@user.com","regUser7","rootpw").execute();
        RegisteredUser user = (RegisteredUser) new GetUser("registered7@user.com").execute().get(0);
        user.setPassword("newPw");

        assertFalse(user.isPassword("notNewPw"));
    }

    /*
        Delete User Tests
     */

    @Test
    public void testDeleteRegisterdUser(){
        new CreateUser("registered3@user.com","regUser3","rootpw").execute();
        new DeleteUser(new GetUser("registered3@user.com").execute().get(0)).execute();

        assertEquals(0, new GetUser("unregistered3@user.com").execute().size());
    }

    @Test
    public void testDeleteUnregisterdUser(){
        new CreateUnregisteredUser("unregisteredUser3", "unregistered3@user.com").execute();
        new DeleteUser(new GetUser("unregistered3@user.com").execute().get(0)).execute();

        assertEquals(0, new GetUser("unregistered3@user.com").execute().size());
    }

    @Override
    protected void tearDown() throws Exception {
        deleteUsers();
    }

    public void deleteUsers(){
        try{
            new DeleteUser(new GetUser("registered1@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("registered2@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("registered3@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("registered4@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("registered5@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("registered6@user.com").execute().get(0)).execute();

            new DeleteUser(new GetUser("unregistered1@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("unregistered2@user.com").execute().get(0)).execute();
            new DeleteUser(new GetUser("unregistered3@user.com").execute().get(0)).execute();
        }catch (Exception e){}
    }
}
