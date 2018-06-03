package com.eventplaner.restControllers;

import com.eventplaner.tasks.userTasks.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class UserController {

    /*
        Create
     */
    @PostMapping("/user/signup")
    public void createRegisteredUser(@Autowired HttpServletResponse response, @RequestParam(value="email") String email, @RequestParam(value="username") String username, @RequestParam(value="password") String password) throws IOException {
/*
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);
*/
        new CreateUser(email, username, password).execute();
        response.sendRedirect("/login");
    }

    @GetMapping("/user/createUnregisteredUser")
    public boolean createUnregisteredUser(HttpServletRequest request){
        return true;
    }

    /*
        Modify
     */
    @GetMapping("/user/registerUser")
    public boolean registerUser(HttpServletRequest request){
        return true;
    }

    @GetMapping("/user/resetUser")
    public boolean resetUser(HttpServletRequest request){
        return true;
    }

    /*
        Remove
     */
    @GetMapping("/user/deleteUser")
    public boolean deleteUser(HttpServletRequest request){
        return true;
    }

    /*
        Misc.
     */
    @PostMapping("/user/login")
    public boolean login(HttpServletRequest request){
        return true;
    }

}
