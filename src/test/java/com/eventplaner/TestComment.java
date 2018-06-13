package com.eventplaner;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.*;
import com.eventplaner.tasks.userTasks.*;
import com.eventplaner.tasks.commentTasks.*;
import com.eventplaner.tasks.pollTasks.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
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

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentSystemRepository commentSystemRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Setting up: ");
        deleteObjects();
    }

    @Test
    public void testCreateComment(){
        //Creating User
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw", registeredUserRepository, userRepository).execute();
        RegisteredUser user = registeredUserRepository.findByEmail("registered1@user.com");
        assertEquals(user.getEmail(), "registered1@user.com");

        //Creting Poll
        new CreatePoll(user, "poll_1", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_1").get(0);
        assertEquals(poll.getName(), "poll_1");

        //Adding Comment
        new AddComment(poll, user, "Hi!", commentRepository, commentSystemRepository).execute();
        assertEquals("Hi!", commentRepository.findAllByComment("Hi!").get(0).getComment());
    }

    @Test
    public void testRemoveComment(){
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw", registeredUserRepository, userRepository).execute();
        RegisteredUser user = registeredUserRepository.findByEmail("registered1@user.com");

        //Creting Poll
        new CreatePoll(user, "poll_1", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_1").get(0);

        //Adding Comment
        new AddComment(poll, user, "Hi!", commentRepository, commentSystemRepository).execute();

        System.out.println(commentRepository.findAllByComment("Hi!").get(0));

        //Deleting Comment
        new RemoveComment(commentRepository.findAllByComment("Hi!").get(0), commentRepository).execute();

        assertEquals(0, commentRepository.findAllByComment("Hi!").size());
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
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw", registeredUserRepository, userRepository).execute();
        RegisteredUser user = registeredUserRepository.findByEmail("registered1@user.com");

        //Creting Poll
        new CreatePoll(user, "poll_1", "poll", true, pollRepository).execute();
        Poll poll = pollRepository.findAllByName("poll_1").get(0);

        //Adding Comment
        new AddComment(poll, user, "Hi!", commentRepository, commentSystemRepository).execute();

        CommentSystem cmnts = new CommentSystem();

        commentSystemRepository.save(cmnts);

        new ReplyToComment(commentRepository.findAllByComment("Hi!").get(0), new Comment(user, "Hi!II"/*, cmnts*/), commentRepository, commentSystemRepository).execute();

        assertEquals("Hi!II",commentRepository.findAllByComment("Hi!").get(0).getSubCommentSystem().getComments().get(0).getComment());

        System.out.println(commentRepository.findAllByComment("Hi!II").get(0));

        //Deleting Comment
        new RemoveComment(commentRepository.findAllByComment("Hi!II").get(0), commentRepository).execute();

        assertEquals(0, commentRepository.findAllByComment("Hi!II").size());
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
