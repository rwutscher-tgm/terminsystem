package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.UnregisteredUser;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.Task;

/**
 * Erstellt einen neuen unregistrierten Benutzer
 */
public class CreateUnregisteredUser implements Task {

    private String email;
    private String uid;
    private UserRepository userRepository;

    /**
     * Der Konstruktor für den CreateUnregisteredUser Task
     * @param email Die Email des unregistrierten Benutzers
     * @param userRepository Das Repository indem der Benutzer gespeichert werden soll
     */
    public CreateUnregisteredUser(String email, UserRepository userRepository) {
        this.email = email;
        this.userRepository = userRepository;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        UnregisteredUser user = new UnregisteredUser(this.email);
        userRepository.save(user);
    }
}
