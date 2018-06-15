package com.eventplaner.tasks.userTasks;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.Task;
import com.eventplaner.tasks.notificationTasks.SendSignUpSuccess;

/**
 * Erstellt einen registrierten Benutzer
 */
public class CreateUser implements Task {

    private String userid;
    private String email;
    private String username;
    private String password;
    private RegisteredUserRepository registeredUserRepository;
    private UserRepository userRepository;

    /**
     * Ein Konstruktor für den CreateUser Task
     * @param userid Die ID des erstellten Benutzers
     * @param email Die Email des erstellten Benutzers
     * @param username Der Username des erstellten Benutzers
     * @param password Das Passwort des erstellten Benutzers
     * @param registeredUserRepository Das Repository indem der Benutzer gespeichert werden soll
     * @param userRepository ?
     */
    public CreateUser(String userid, String email, String username, String password, RegisteredUserRepository registeredUserRepository, UserRepository userRepository) {
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.registeredUserRepository = registeredUserRepository;
        this.userRepository = userRepository;
    }

    /**
     * Ein Konstruktor für den CreateUser Task
     * @param email Die Email des erstellten Benutzers
     * @param username Der Username des erstellten Benutzers
     * @param password Das Passwort des erstellten Benutzers
     * @param registeredUserRepository Das Repository indem der Benutzer gespeichert werden soll
     */
    public CreateUser(String email, String username, String password, RegisteredUserRepository registeredUserRepository) {
        this.email = email;
        this.username = username;
        this.password = password;
        this. registeredUserRepository = registeredUserRepository;
    }

    /*@Autowired
    UserRepository userRepository;*/

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        RegisteredUser user;
        if(userid != null){
            user = new RegisteredUser(this.userid, this.email, this.password, this.username);
            userRepository.delete(user);
//            System.out.println(user.getUserID());
        }else{
            user = new RegisteredUser(this.email, this.password, this.username);
        }
//        System.out.println(user.getUserID());
        //userRepository.save(user);
        //new SendSignUpSuccess(user).execute();
        registeredUserRepository.save(user);
    }
}
