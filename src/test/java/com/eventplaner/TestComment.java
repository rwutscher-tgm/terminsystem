package com.eventplaner;

import com.eventplaner.model.Comment;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.commentTasks.AddComment;
import com.eventplaner.tasks.commentTasks.EditComment;
import com.eventplaner.tasks.commentTasks.GetComment;
import com.eventplaner.tasks.pollTasks.CreatePoll;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.GetUser;
import junit.framework.TestCase;
import org.junit.Test;

public class TestComment extends TestCase{

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
        new CreateUser("userCreatedWithID","registered1@user.com","regUser1","rootpw").execute();
        RegisteredUser user = (RegisteredUser) new GetUser("registered1@user.com").execute().get(0);

        //Creting Poll
        new CreatePoll(user, "poll_1", "poll", true);
        Poll poll = new GetPoll("registered1@user.com").execute().get(0);

        //Adding Comment
        new AddComment(poll, user, "Hi!").execute();
        //Comment comment = new GetComment(poll).execute().get(0);

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

    }
}
