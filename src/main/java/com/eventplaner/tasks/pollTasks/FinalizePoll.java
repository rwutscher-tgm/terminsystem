package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.Task;
import com.eventplaner.tasks.notificationTasks.SendPollFailed;
import com.eventplaner.tasks.notificationTasks.SendPollFinalizedUpdate;

public class FinalizePoll implements Task{

    private Poll poll;
    private boolean success;

    public FinalizePoll(Poll poll, boolean success) {
        this.poll = poll;
        this.success = success;
    }

    @Override
    public void execute() {
        if(success){
            new SendPollFinalizedUpdate(this.poll).execute();
        }else{
            new SendPollFailed(this.poll).execute();
        }
        new DeleteObject<>(this.poll).execute();
    }
}
