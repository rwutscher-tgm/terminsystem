package com.eventplaner.restControllers;

import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
public class CommentController {

    @PostMapping("/comment/createComment")
    public boolean createComment(HttpServletRequest request){
        return true;
    }

    @PostMapping("/comment/createSubComment")
    public boolean createSubComment(HttpServletRequest request){
        return true;
    }

    @PostMapping("/comment/editComment")
    public boolean editComment(HttpServletRequest request){
        return true;
    }

    @PostMapping("/comment/removeComment")
    public boolean removeComment(HttpServletRequest request){
        return true;
    }

}
