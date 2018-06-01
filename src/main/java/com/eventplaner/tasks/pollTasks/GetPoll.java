package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.GetterTask;

import java.util.ArrayList;

public class GetPoll implements GetterTask{

    private String pid;

    public GetPoll(){

    }

    public GetPoll(String pid){
        this.pid = pid;
    }

    @Override
    public ArrayList<Poll> execute() {
        if(this.pid != null){
            //TODO: Implement get Poll by pollId
        }else{
            //TODO: Implement get all Polls
        }
        return null;
    }
}
