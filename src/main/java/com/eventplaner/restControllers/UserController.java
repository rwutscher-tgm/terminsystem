package com.eventplaner.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
public class UserController {

    /*
        Create
     */
    @PostMapping("/user/createRegisteredUser")
    public boolean createRegisteredUser(HttpServletRequest request){
        return true;
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
