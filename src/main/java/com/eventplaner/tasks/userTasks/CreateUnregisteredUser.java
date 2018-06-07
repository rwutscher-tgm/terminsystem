package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;

public class CreateUnregisteredUser implements Task {

    private String email;
    private String uid;
    private UserRepository userRepository;

    public CreateUnregisteredUser(String email, UserRepository userRepository) {
        this.email = email;
        this.userRepository = userRepository;
    }

    @Override
    public void execute() {
        UnregisteredUser user = new UnregisteredUser(this.email);
        userRepository.save(user);
    }
}
