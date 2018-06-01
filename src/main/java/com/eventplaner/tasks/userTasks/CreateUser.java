package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;

public class CreateUser implements Task {

    private String userid;
    private String email;
    private String username;
    private String password;

    public CreateUser(String userid, String email, String username, String password) {
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    //TODO: Implement Constructor without userID

    @Override
    public void execute() {

    }
}
