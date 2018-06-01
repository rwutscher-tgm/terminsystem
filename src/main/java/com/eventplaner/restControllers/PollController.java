package com.eventplaner.restControllers;

import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
public class PollController {

    /*
        Creating
     */
    @PostMapping("/poll/createPoll")
    public boolean createPoll(HttpServletRequest request){
        return true;
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

    @PostMapping("/poll/VoteForTopic")
    public boolean VoteForTopic(HttpServletRequest request){
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
