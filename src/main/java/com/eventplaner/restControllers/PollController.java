package com.eventplaner.restControllers;

import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
public class PollController {

    /*
        Creating
     */
    @PostMapping("/poll/createPoll")
    public void createPoll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("seas mich gibts auch");
        response.sendRedirect("/");
    }

    /*
        Modifying
     */
    @PostMapping("/poll/addOrganizer")
    public boolean addOrganizer(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/addPollTopic")
    public boolean addPollTopic(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/joinPoll")
    public boolean joinPoll(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/voteForTopic")
    public boolean voteForTopic(HttpServletRequest request){
        System.out.println("Seas ....................................................................................");
        return true;
    }

    /*
        Remove
     */
    @PostMapping("/poll/finalizePoll")
    public boolean finalizePoll(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/leavePoll")
    public boolean leavePoll(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/removeOrganizer")
    public boolean removeOrganizer(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/removePollTopic")
    public boolean removePollTopic(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/removeVoteForTopic")
    public boolean removeVoteForTopic(HttpServletRequest request){
        return true;
    }

}
