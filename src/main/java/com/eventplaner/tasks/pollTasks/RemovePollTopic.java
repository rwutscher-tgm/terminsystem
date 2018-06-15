package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.tasks.Task;

/**
 * Entfernt ein PollTopic
 */
public class RemovePollTopic implements Task{

    private Poll poll;
    private PollTopic pollTopic;
    private PollRepository pollRepository;
    private PollTopicRepository pollTopicRepository;

    /**
     * Der Konstruktor für den RemoveTopic Task
     * @param poll Der Poll aus dem ein Topic entfernt werden soll
     * @param pollTopic Das Topic das entfernt werden soll
     * @param pollRepository Das Repository indem der Poll gespeichert werden soll
     * @param pollTopicRepository Das Repository aus dem das Topic gelöscht werden soll
     */
    public RemovePollTopic(Poll poll, PollTopic pollTopic, PollRepository pollRepository, PollTopicRepository pollTopicRepository) {
        this.poll = poll;
        this.pollTopic = pollTopic;
        this.pollRepository = pollRepository;
        this.pollTopicRepository = pollTopicRepository;
    }

    /**
     * Führt den Taks aus
     */
    @Override
    public void execute() {

        pollTopicRepository.delete(pollTopic);

        this.poll.removePollTopic(pollTopic);
        pollRepository.save(poll);

//        this.poll.
//        this.pollRepository.save(this.poll);
    }
}
