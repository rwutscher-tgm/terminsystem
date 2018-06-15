package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.tasks.Task;

/**
 * Fügt eine Stimme für einen PollTopic hinzu
 */
public class VoteForTopic implements Task{

    private User user;

    private PollTopicRepository repository;

    private PollTopic polltopic;

    /**
     * Der Konstruktor für den VoteForTopic Taks
     * @param user Der User der für ein Topic stimmt
     * @param polltopic Das Topic für das gestimmt wird
     * @param repository Das Repository indem das PollTopic gespeichert werden soll
     */
    public VoteForTopic(User user, PollTopic polltopic, PollTopicRepository repository) {
        this.user = user;
        this.repository = repository;
        this.polltopic = polltopic;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        this.polltopic.addAvailable(user);
        repository.save(this.polltopic);
    }
}