package com.eventplaner.restControllers;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.model.repositories.UserRepository;
import com.eventplaner.tasks.pollTasks.*;
import com.eventplaner.tasks.userTasks.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.EngineContext;
import org.thymeleaf.context.IContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class PollController {

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollTopicRepository pollTopicRepository;

    @Autowired
    UserRepository userRepository;
    /*
        Creating
     */
    @PostMapping("/poll/createPoll")
    public void createPoll(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

        RegisteredUser organizer = (RegisteredUser) new GetUser(user.getName()).execute().get(0);


        boolean status = false;
        if(params.get("status") != null){
            if(params.get("status").equals("on")){
                status = true;
            }
        }




        CreatePoll cp = new CreatePoll(organizer, params.get("PollName"), params.get("Polldesc"), status);

        cp.execute();

        String id = cp.getId();

        Poll poll = new GetPoll(id).execute().get(0);

        System.out.println("Amount of Polls in Database"+new GetPoll().execute().size());

        for(String param : params.values()){
            System.out.println(param);
        }

        for(String param : params.keySet()){
            if(param.substring(0, 6).equals("topic_")){
                System.out.println("Adding Poll Topic: "+params.get(param));
                new AddPollTopic(poll, params.get(param)).execute();
            }
        }

        System.out.println("Amount of Polls in Database"+new GetPoll().execute().size());


        System.out.println("SEAS .............................................. ..................... ...............");
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
    public void joinPoll(@RequestParam(value="poll", required = false) String pollId, Principal user, HttpServletResponse response) throws IOException {

        if(user != null){
            if(!pollRepository.findById(pollId).getParticipants().contains(userRepository.findByUserID(user.getName()))){
                //if user hasn't joined
                new JoinPoll(pollRepository.findById(pollId), new GetUser(user.getName()).execute().get(0), pollRepository).execute();
                response.sendRedirect("/poll?poll="+pollId);
            }else{
                new LeavePoll(pollRepository.findById(pollId), new GetUser(user.getName()).execute().get(0), pollRepository).execute();
                response.sendRedirect("/poll?poll="+pollId);
            }

        }else{
            response.sendRedirect("/poll?poll="+pollId);
        }
    }

    @PostMapping("/poll/getjoined")
    public boolean getjoined(@RequestParam(value="poll", required = false) String pollId, Principal user, HttpServletResponse response) throws IOException {
        return pollRepository.findById(pollId).getParticipants().contains(userRepository.findByUserID(user.getName()));
    }

    @PostMapping("/poll/voteForTopic")
    public boolean voteForTopic(@RequestParam(value="topic") String topic, @RequestParam(value="voted") boolean voted, Principal user){
        System.out.println("Seas ....................................................................................");
        try{
            if(user != null){
                if(!pollTopicRepository.findById(topic).getAvailables().contains(userRepository.findByEmail(user.getName()))){
                    // if User hasnt voted for topic jet

                    if(voted){
                        new VoteForTopic(
                                userRepository.findByEmail(user.getName()),
                                pollTopicRepository,
                                pollTopicRepository.findById(topic)
                        ).execute();
                    }
                }else{
                    // if User has already voted for topic
                    // if User hasnt voted for topic jet

                    if(!voted){
                        new RemoveVoteForTopic(
                                userRepository.findByEmail(user.getName()),
                                pollTopicRepository,
                                pollTopicRepository.findById(topic)
                        ).execute();
                    }

                }

            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/poll/gettopicvote")
    public boolean getTopicVote(@RequestParam(value="topic") String topic, Principal user){
        if(!pollTopicRepository.findById(topic).getAvailables().contains(userRepository.findByEmail(user.getName()))){
            return false;
        }else{
            return true;
        }
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
