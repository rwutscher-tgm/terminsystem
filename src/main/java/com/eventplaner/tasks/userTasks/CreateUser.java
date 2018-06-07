package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.tasks.Task;

public class CreateUser implements Task {

    private String userid;
    private String email;
    private String username;
    private String password;
    private RegisteredUserRepository registeredUserRepository;

    public CreateUser(String userid, String email, String username, String password, RegisteredUserRepository registeredUserRepository) {
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.registeredUserRepository = registeredUserRepository;
    }

    public CreateUser(String email, String username, String password, RegisteredUserRepository registeredUserRepository) {
        this.email = email;
        this.username = username;
        this.password = password;
        this. registeredUserRepository = registeredUserRepository;
    }

    /*@Autowired
    UserRepository userRepository;*/

    @Override
    public void execute() {
        RegisteredUser user;
        if(userid != null){
            user = new RegisteredUser(this.userid, this.email, this.password, this.username);
            System.out.println(user.getUserID());
        }else{
            user = new RegisteredUser(this.email, this.username, this.password);
        }
        System.out.println(user.getUserID());
        //userRepository.save(user);
        registeredUserRepository.save(user);
    }
}
