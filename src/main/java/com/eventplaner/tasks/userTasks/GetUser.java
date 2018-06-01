package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.GetterTask;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

public class GetUser implements GetterTask {

    private String uid;
    private String email;
    private Poll poll;

    public GetUser(){

    }

    public GetUser(Poll poll){
        this.poll = poll;
    }

    public GetUser(String string){
        if(isEmail(string)){
            this.email = string;
        }else{
            this.uid = string;
        }
    }

    public boolean isEmail(String string) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (string == null)
            return false;
        return pat.matcher(string).matches();
    }

    @Override
    public ArrayList<User> execute() {
        if(this.uid != null){
            //TODO: Implement get User by userID
        }else if(this.email != null){
            //TODO: Implement get User by email
        }else if(this.poll != null){
            //TODO: Implement get all Users in a Poll
        }else{
            //TODO: Implement get all Users
        }
        return null;
    }
}
