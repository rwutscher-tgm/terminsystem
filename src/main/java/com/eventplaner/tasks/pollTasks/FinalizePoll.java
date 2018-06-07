package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.Task;
import com.eventplaner.tasks.notificationTasks.SendPollFailed;
import com.eventplaner.tasks.notificationTasks.SendPollFinalizedUpdate;

public class FinalizePoll implements Task{

    private Poll poll;
    private boolean success;
    private PollRepository pollRepository;

    public FinalizePoll(Poll poll, boolean success, PollRepository pollRepository) {
        this.poll = poll;
        this.success = success;
        this.pollRepository = pollRepository;
    }

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
