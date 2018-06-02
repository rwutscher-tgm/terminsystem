package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.GetterTask;

import java.util.ArrayList;

public class GetOrganizer implements GetterTask{

    private Poll poll;

    public GetOrganizer(Poll poll) {
        this.poll = poll;
    }

    @Override
    public ArrayList<RegisteredUser> execute() {
        return null;
    }
}
