package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.DeleteObject;
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
        if(success){

            PollTopic mostVotedTopic = this.poll.getPollTopics().get(0);

            for(PollTopic topic: this.poll.getPollTopics()){
                if(topic.getAvailables().size() > mostVotedTopic.getAvailables().size()){
                    mostVotedTopic = topic;
                }

            }
            new SendPollFinalizedUpdate(this.poll).execute();
        }else{
            new SendPollFailed(this.poll).execute();
        }
        pollRepository.delete(this.poll);
    }
}
