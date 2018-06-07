package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.tasks.DeleteObjecta;
import com.eventplaner.tasks.Task;

public class DeleteUser implements Task{

    private User user;
    private RegisteredUserRepository registeredUserRepository;

    public DeleteUser(User user, RegisteredUserRepository registeredUserRepository) {
        this.user = user;
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    public void execute() {
        registeredUserRepository.delete((RegisteredUser) user);
    }
}
