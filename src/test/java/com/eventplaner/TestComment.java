package com.eventplaner;

import com.eventplaner.model.Comment;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
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
        Comment comment = new GetComment(poll).execute().get(0);

        //Changing Comment
        new EditComment(comment, "Bye!").execute();
        comment = new GetComment(poll).execute().get(0);

        assertEquals(comment.getComment(), "Bye!");
    }

    @Test
    public void testReplyToComment(){

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
