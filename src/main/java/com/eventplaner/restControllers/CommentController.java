package com.eventplaner.restControllers;

import com.eventplaner.model.repositories.*;
import com.eventplaner.tasks.commentTasks.AddComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentSystemRepository commentSystemRepository;

    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollTopicRepository pollTopicRepository;

    @PostMapping("/comment/createComment")
    public void createComment(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

//        new AddComment(new GetPoll(params.get("poll")).execute().get(0), (RegisteredUser) new GetUser(user.getName()).execute().get(0), params.get("comment")).execute();

        new AddComment(
                pollRepository.findById(
                    params.get("poll")
                ),
                registeredUserRepository.findByEmail(
                        user.getName()
                ),
                params.get("comment"),
                commentRepository,
                commentSystemRepository
        ).execute();

        // CommentSystem system, RegisteredUser author, String comment, CommentRepository commentRepository, CommentSystemRepository commentSystemRepository
        response.sendRedirect("/poll?poll="+params.get("poll"));
    }

    @PostMapping("/poll/gettopicvote")

    public boolean getTopicVote(@RequestParam(value="topic") String topic, Principal user){
        if(!pollTopicRepository.findById(topic).getAvailables().contains(userRepository.findByEmail(user.getName()))){
            return false;
        }else{
            return true;
        }
    }


    @PostMapping("/comment/createSubComment")
    public void createSubComment(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

        System.out.println("SEAS :..................................................................................:");
        System.out.println(params.get("commentSystem"));
//        System.out.print(new GetCommentSystem().execute().size());


//        new AddComment(new GetCommentSystem(params.get("commentSystem")).execute().get(0), (RegisteredUser) new GetUser(user.getName()).execute().get(0), params.get("comment")).execute();

        new AddComment(commentSystemRepository.findByCommentSystemID(params.get("commentSystem")),
                registeredUserRepository.findByEmail(user.getName()),
                params.get("comment"),
                commentRepository,
                commentSystemRepository).execute();

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
