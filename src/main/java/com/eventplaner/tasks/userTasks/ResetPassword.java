package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.Task;

/**
 * Unimplemented
 */
public class ResetPassword implements Task{

    private RegisteredUser user;

    private String newPassword;

    public ResetPassword(RegisteredUser user, String newPassword) {
        this.user = user;
        this.newPassword = newPassword;
    }

    @Override
    public void execute() {

    }
}
