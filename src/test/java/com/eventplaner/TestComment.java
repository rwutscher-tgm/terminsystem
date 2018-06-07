package com.eventplaner;

import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.model.repositories.UserRepository;
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
public class TestComment extends TestCase{

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollTopicRepository pollTopicRepository;

    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Setting up: ");
        deleteObjects();
    }

    @Test
    public void testCreateComment(){

    }

    @Test
    public void testRemoveComment(){

    }

    @Test
    public void testChangeComment(){
        //Creating User
//        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw", registeredUserRepository, userRepository).execute();
//        RegisteredUser user = (RegisteredUser) new GetUser("registered1@user.com").execute().get(0);

        //Creting Poll
//        new CreatePoll(user, "poll_1", "poll", true, pollRepository);
//        Poll poll = new GetPoll("registered1@user.com").execute().get(0);

        //Adding Comment
//        new AddComment(poll, user, "Hi!").execute();
        //Comment comment = new GetComment(poll).execute().get(0);*/

        //Changing Comment
        //new EditComment(comment, "Bye!").execute();
        //comment = new GetComment(poll).execute().get(0);

        //assertEquals(comment.getComment(), "Bye!");
    }

    @Test
    public void testReplyToComment(){

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
    }
}
