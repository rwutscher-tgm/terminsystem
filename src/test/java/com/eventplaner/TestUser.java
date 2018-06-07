package com.eventplaner;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.userTasks.CreateUnregisteredUser;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.DeleteUser;
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
public class TestUser extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Setting up: ");
        deleteUsers();
    }

    /*
        Create User Tests
     */
    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testRepositoryNotNull(){
        if(registeredUserRepository != null){
            System.out.println("seasseas123");
        }
        assertNotNull(registeredUserRepository);
        RegisteredUser user = new RegisteredUser("rwutscher@student.tgm.ac.at", "rootpw", "Richard Wutscher");
        registeredUserRepository.save(user);
//        System.out.println(registeredUserRepository.findByEmail("rwutscher@student.tgm.ac.at").getUsername());
        registeredUserRepository.delete(user);
    }

    @Test
    public void testCreateRegisteredUserWithId() {

        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw", registeredUserRepository).execute();

        assertEquals("userCreatedWithID", registeredUserRepository.findByEmail("registered1@user.com").getUserID());
    }

    @Test
    public void testCreateRegisteredUserWithoutId() {
        new CreateUser("registered2@user.com","regUser2","rootpw", registeredUserRepository).execute();

        assertEquals("registered2@user.com", registeredUserRepository.findByEmail("registered2@user.com").getEmail());
    }

//    @Test
//    public void testCreateUnregisteredUser() {
//        new CreateUnregisteredUser("unregistered1@user.com").execute();
//
//        assertEquals(1, new GetUser("unregistered1@user.com").execute().size());
//    }

    /*
        Login Tests
     */



    /*
        Password Tests
     */

//    @Test
//    public void testIsPasswordRight(){
//        new CreateUser("registered4@user.com","regUser4","rootpw",registeredUserRepository).execute();
//        RegisteredUser user = registeredUserRepository.findByEmail("registered4@user.com");
//
//        assertTrue(user.isPassword("rootpw"));
//    }
//
//    @Test
//    public void testIsPasswordWrong(){
//        new CreateUser("registered5@user.com","regUser5","rootpw",registeredUserRepository).execute();
//        RegisteredUser user = (RegisteredUser) new GetUser("registered5@user.com").execute().get(0);
//
//        assertFalse(user.isPassword("RootPw"));
//    }
//
//    @Test
//    public void testChangePasswordRight(){
//        new CreateUser("registered6@user.com","regUser6","rootpw",registeredUserRepository).execute();
//        RegisteredUser user = (RegisteredUser) new GetUser("registered6@user.com").execute().get(0);
//        user.setPassword("newPw");
//
//        assertTrue(user.isPassword("newPw"));
//    }

//    @Test
//    public void testChangePasswordWrong(){
//        new CreateUser("registered7@user.com","regUser7","rootpw",registeredUserRepository).execute();
//        RegisteredUser user = (RegisteredUser) new GetUser("registered7@user.com").execute().get(0);
//        user.setPassword("newPw");
//
//        assertFalse(user.isPassword("notNewPw"));
//    }

    /*
        Delete User Tests
     */

    @Test
    public void testDeleteRegisterdUser(){
        new CreateUser("registered3@user.com","regUser3","rootpw",registeredUserRepository).execute();
        new DeleteUser(registeredUserRepository.findByEmail("registered3@user.com"), registeredUserRepository).execute();

        assertNull(registeredUserRepository.findByEmail("registered3@user.com"));
    }

//    @Test
//    public void testDeleteUnregisterdUser(){
//        new CreateUnregisteredUser("unregistered2@user.com").execute();
//        System.out.println(new GetUser("unregistered2@user.com").execute().get(0).getEmail());
//        new DeleteUser(new GetUser("unregistered2@user.com").execute().get(0)).execute();
//
//        assertEquals(0, new GetUser("unregistered2@user.com").execute().size());
//    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteUsers();
    }

    public void deleteUsers(){
        String[] users = new String[]{
                "registered1@user.com",
                "registered2@user.com",
                "registered3@user.com",
                "registered4@user.com",
                "registered5@user.com",
                "registered6@user.com",
                "registered7@user.com",

                "unregistered1@user.com",
                "unregistered2@user.com"
        };
        for(String user: users){
            try{
                registeredUserRepository.delete(registeredUserRepository.findByEmail(user));
                System.out.println("Deleting user");
            }catch (Exception e){}
        }

        System.out.println("Cleaning up: ");
//        System.out.println("Amount of users in DB: " + new GetUser().execute().size());

    }
}
