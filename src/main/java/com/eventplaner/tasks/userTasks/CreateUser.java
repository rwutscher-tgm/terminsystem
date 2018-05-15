package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;

public class CreateUser implements Task {

    private RegisteredUser user;

    public CreateUser(RegisteredUser user) {
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
