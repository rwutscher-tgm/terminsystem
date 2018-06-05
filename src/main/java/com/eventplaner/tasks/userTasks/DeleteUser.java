package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.Task;

public class DeleteUser implements Task{

    private User user;

    public DeleteUser(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        new DeleteObject<>(this.user).execute();
    }
}
