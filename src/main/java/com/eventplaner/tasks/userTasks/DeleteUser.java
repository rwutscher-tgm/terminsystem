package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.tasks.Task;

/**
 * Löscht einen Benutzer
 */
public class DeleteUser implements Task{

    private User user;
    private RegisteredUserRepository registeredUserRepository;

    /**
     * Der Konstruktor Für den DeleteUser Task
     * @param user Der Benutzer der gelöscht werden soll
     * @param registeredUserRepository Das Repository aus dem der Benutzer gelöscht werden soll
     */
    public DeleteUser(User user, RegisteredUserRepository registeredUserRepository) {
        this.user = user;
        this.registeredUserRepository = registeredUserRepository;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        registeredUserRepository.delete((RegisteredUser) user);
    }
}
