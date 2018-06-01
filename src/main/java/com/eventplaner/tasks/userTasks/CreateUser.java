package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.SaveObject;
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

    public CreateUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        RegisteredUser user = new RegisteredUser(this.email, this.username, this.password);
        user.setUserID(this.userid);
        new SaveObject<>(user).execute();
    }
}
