package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.GetterTask;

import java.util.ArrayList;

public class GetComment implements GetterTask{

    private User user;
    private Poll poll;
    private String cid;

    public GetComment(){

    }

    public GetComment(User user) {
        this.user = user;
    }

    public GetComment(Poll poll) {
        this.poll = poll;
    }

    public GetComment(String cid) {
        this.cid = cid;
    }

    @Override
    public ArrayList<Comment> execute() {
        if(this.user != null){
            //TODO: Implement get all Comments from a User
        }else if(this.poll != null){
            //TODO: Implement get all Comments from a Poll
        }else if(this.cid != null){
            //TODO: Implement get Comment by commentId
        }else{
            //TODO: Implement get all Comments
        }

        return null;
    }
}
