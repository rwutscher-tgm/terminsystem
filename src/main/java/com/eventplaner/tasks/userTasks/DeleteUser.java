package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.Task;

public class DeleteUser implements Task{

    private RegisteredUser user;

    public DeleteUser(RegisteredUser user) {
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
