package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;
import com.eventplaner.tasks.notificationTasks.SendPollFailed;
import com.eventplaner.tasks.notificationTasks.SendPollFinalizedUpdate;

/**
 * Beendet den Poll und teilt das Ergebniss den Teilnemern mit
 */
public class FinalizePoll implements Task{

    private Poll poll;
    private boolean success;
    private PollRepository pollRepository;

    /**
     * Der Konstruktor für den FinalizePoll Task
     * @param poll Der Poll der beendet werden soll
     * @param success Ob der Poll ein Ergebniss Produziert hat oder nicht.
     * @param pollRepository Das Repository indem der Poll gespeichert werden soll
     */
    public FinalizePoll(Poll poll, boolean success, PollRepository pollRepository) {
        this.poll = poll;
        this.success = success;
        this.pollRepository = pollRepository;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        System.out.println("\n...\n...\n...\n...\n...\n Finalizing Poll ");
        if(success){

            PollTopic mostVotedTopic = this.poll.getPollTopics().get(0);

            for(PollTopic topic: this.poll.getPollTopics()){
                if(topic.getAvailables().size() > mostVotedTopic.getAvailables().size()){
                    mostVotedTopic = topic;
                }

            }
            for(User participant: this.poll.getParticipants()){
                new SendPollFinalizedUpdate(this.poll, mostVotedTopic, participant).execute();
                System.out.println("\n...\n...\n...\n...\n...\n Sending Finalize Success");
            }
        }else{
            for(User participant: this.poll.getParticipants()){
                new SendPollFailed(this.poll, participant).execute();
            }

        }
        pollRepository.delete(this.poll);
    }
}
