package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.tasks.Task;

public class RegisterUser implements Task{

    private UnregisteredUser user;

    public RegisterUser(UnregisteredUser user) {
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
