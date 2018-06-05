package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateUser implements Task {

    private String userid;
    private String email;
    private String username;
    private String password;

    public CreateUser(String userid, String email, String username, String password) {
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public CreateUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /*@Autowired
    UserRepository userRepository;*/

    @Override
    public void execute() {
        RegisteredUser user;
        if(userid != null){
            user = new RegisteredUser(this.userid, this.email, this.username, this.password);
            System.out.println(user.getUserID());
        }else{
            user = new RegisteredUser(this.email, this.username, this.password);
        }
        System.out.println(user.getUserID());
        //userRepository.save(user);
        new SaveObject<>(user).execute();
    }
}
