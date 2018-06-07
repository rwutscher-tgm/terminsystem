package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.PollTopic;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.tasks.Task;

public class RemovePollTopic implements Task{

    private Poll poll;
    private PollTopic pollTopic;
    private PollRepository pollRepository;
    private PollTopicRepository pollTopicRepository;

    public RemovePollTopic(Poll poll, PollTopic pollTopic, PollRepository pollRepository, PollTopicRepository pollTopicRepository) {
        this.poll = poll;
        this.pollTopic = pollTopic;
        this.pollRepository = pollRepository;
        this.pollTopicRepository = pollTopicRepository;
    }

    @Override
    public void execute() {

        pollTopicRepository.delete(pollTopic);

        this.poll.removePollTopic(pollTopic);
        pollRepository.save(poll);

//        this.poll.
//        this.pollRepository.save(this.poll);
    }
}
