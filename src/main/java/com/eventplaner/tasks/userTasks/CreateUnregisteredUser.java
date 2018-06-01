package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;

public class CreateUnregisteredUser implements Task {

    private String email;
    private String uid;

    public CreateUnregisteredUser(String email) {
        this.email = email;
    }

    @Override
    public void execute() {
        UnregisteredUser user = new UnregisteredUser(this.email);
        new SaveObject<>(user).execute();
    }
}
