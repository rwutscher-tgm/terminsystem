package com.eventplaner.restControllers;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.pollTasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class PollController {

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollTopicRepository pollTopicRepository;

    @Autowired
    RegisteredUserRepository registeredUserRepository;

    @Autowired
    UserRepository userRepository;

    /*
        Creating
     */
    @PostMapping("/poll/createPoll")
    @ResponseBody
    public void createPoll(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

        RegisteredUser organizer = registeredUserRepository.findByEmail(user.getName());


        boolean status = false;
        if (params.get("status") != null) {
            if (params.get("status").equals("on")) {
                status = true;
            }
        }


        CreatePoll cp = new CreatePoll(organizer, params.get("PollName"), params.get("Polldesc"), status, pollRepository);

        cp.execute();

        String id = cp.getId();

        Poll poll = pollRepository.findById(id);

        for (String param : params.keySet()) {
            if (param.substring(0, 6).equals("topic_")) {
                new AddPollTopic(poll, params.get(param), pollRepository, pollTopicRepository).execute();
            }
        }

        response.sendRedirect("/");
    }

    /*
        Modifying
     */
    @PostMapping("/poll/addOrganizer")
    @ResponseBody
    public boolean addOrganizer(HttpServletRequest request) {
        return true;
    }

    @PostMapping("/poll/addPollTopic")
    @ResponseBody
    public boolean addPollTopic(HttpServletRequest request) {
        return true;
    }

    @PostMapping("/poll/joinPoll")
    @ResponseBody
    public void joinPoll(@RequestParam(value = "poll", required = false) String pollId, Principal user, HttpServletResponse response) throws IOException {

        /*if(user != null){
            new JoinPoll(pollRepository.findById(pollId), userRepository.findByEmail(user.getName()), pollRepository).execute();
            response.sendRedirect("/poll?poll="+pollId);
        }*/

        if (user != null) {
            if (!pollRepository.findById(pollId).getParticipants().contains(userRepository.findByUserID(user.getName()))) {
                //if user hasn't joined
                new JoinPoll(pollRepository.findById(pollId), userRepository.findByEmail(user.getName()), pollRepository).execute();
                response.sendRedirect("/poll?poll=" + pollId);
            } else {
                new LeavePoll(pollRepository.findById(pollId), userRepository.findByEmail(user.getName()), pollRepository).execute();
                response.sendRedirect("/poll?poll=" + pollId);
            }
        } else {
            new JoinPoll(pollRepository.findById(pollId), userRepository.findByEmail(user.getName()), pollRepository).execute();
            response.sendRedirect("/poll?poll=" + pollId);
        }


        //response.sendRedirect("/poll?poll="+pollId);
    }

    @PostMapping("/poll/gettopicvote")
    @ResponseBody
    public boolean getTopicVote(@RequestParam(value="topic") String topic, Principal user){
        if(!pollTopicRepository.findById(topic).getAvailables().contains(userRepository.findByEmail(user.getName()))){
            return false;
        }else{
            return true;
        }
    }


    /*@PostMapping("/poll/voteForTopic")
    public boolean voteForTopic(@RequestParam Map<String, String> params, Principal user){
        try{
            if(user != null){
                System.out.print(params.get("topic"));
                new VoteForTopic(userRepository.findByEmail(user.getName()),
                        pollTopicRepository.findById(params.get("topic")),
                        pollTopicRepository).execute();
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }*/

    @PostMapping("/poll/voteForTopic")
    @ResponseBody
    public boolean voteForTopic(@RequestParam(value = "topic") String topic, @RequestParam(value = "voted") boolean voted, Principal user) {
        //System.out.println("Seas ....................................................................................");
        try {
            if (user != null) {
                if (!pollTopicRepository.findById(topic).getAvailables().contains(userRepository.findByEmail(user.getName()))) {
                    // if User hasnt voted for topic jet
                    if (voted) {
                        new VoteForTopic(
                                userRepository.findByEmail(user.getName()),
                                pollTopicRepository.findById(topic),
                                pollTopicRepository
                        ).execute();
                    }
                } else {
                    // if User has already voted for topic
                    if (!voted) {
                        new RemoveVoteForTopic(
                                userRepository.findByEmail(user.getName()),
                                pollTopicRepository.findById(topic),
                                pollTopicRepository
                        ).execute();
                    }
                }
                return true;
            }else return false;
        } catch (Exception e) {
            return false;
        }

    }

    @PostMapping("/poll/getjoined")
    @ResponseBody
    public boolean getjoined(@RequestParam(value="poll", required = false) String pollId, Principal user, HttpServletResponse response) throws IOException {

        return pollRepository.findById(pollId).getParticipants().contains(userRepository.findByUserID(user.getName()));

        //response.sendRedirect("/poll?poll="+pollId);

    }


    /*
        Remove
     */
    @GetMapping("/poll/finalizePoll")
    @ResponseBody
    public void finalizePoll(@RequestParam Map<String, String> params, Principal user){
        new FinalizePoll(pollRepository.findById(params.get("poll")), true, pollRepository).execute();
    }

    @PostMapping("/poll/leavePoll")
    @ResponseBody
    public boolean leavePoll(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/removeOrganizer")
    @ResponseBody
    public boolean removeOrganizer(HttpServletRequest request){
        return true;
    }

    @PostMapping("/poll/removePollTopic")
    @ResponseBody
    public boolean removePollTopic(HttpServletRequest request){

        return true;
    }

    @PostMapping("/poll/removeVoteForTopic")
    @ResponseBody
    public boolean removeVoteForTopic(HttpServletRequest request){
        return true;
    }

}
