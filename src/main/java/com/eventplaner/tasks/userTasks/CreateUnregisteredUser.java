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

    public CreateUnregisteredUser(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    @Override
    public void execute() {
        
    }
}
