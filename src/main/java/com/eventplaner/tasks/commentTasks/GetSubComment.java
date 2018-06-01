package com.eventplaner.tasks.commentTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.tasks.GetterTask;

import java.util.ArrayList;

public class GetSubComment implements GetterTask{

    private Comment comment;
    private String cid;

    public GetSubComment(Comment comment) {
        this.comment = comment;
    }

    public GetSubComment(String cid) {
        this.cid = cid;
    }

    @Override
    public ArrayList<Comment> execute() {

        if(this.comment != null){
            //TODO: Implement get all Subcomments from Comment
        }else{
            //TODO: Implement get all Subcomments from Comment by commentId
        }

        return null;
    }
}
