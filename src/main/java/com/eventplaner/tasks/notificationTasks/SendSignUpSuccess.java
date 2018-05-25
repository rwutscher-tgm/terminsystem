package com.eventplaner.tasks.notificationTasks;


import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.Task;


public class SendSignUpSuccess implements Task{

    private RegisteredUser user;

    public SendSignUpSuccess(RegisteredUser user){
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
