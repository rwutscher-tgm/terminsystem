package com.eventplaner.restControllers;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.commentTasks.AddComment;
import com.eventplaner.tasks.commentTasks.GetCommentSystem;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.userTasks.GetUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class CommentController {

    @PostMapping("/comment/createComment")
    public void createComment(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

        new AddComment(new GetPoll(params.get("poll")).execute().get(0), (RegisteredUser) new GetUser(user.getName()).execute().get(0), params.get("comment")).execute();

        response.sendRedirect("/poll?poll="+params.get("poll"));
    }

    @PostMapping("/comment/createSubComment")
    public void createSubComment(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

        System.out.println("SEAS :..................................................................................:");
        System.out.println(params.get("commentSystem"));
        System.out.print(new GetCommentSystem().execute().size());


        new AddComment(new GetCommentSystem(params.get("commentSystem")).execute().get(0), (RegisteredUser) new GetUser(user.getName()).execute().get(0), params.get("comment")).execute();

        response.sendRedirect("/poll?poll="+params.get("poll"));
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
