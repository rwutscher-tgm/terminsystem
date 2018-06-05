package com.eventplaner.restControllers;

import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.pollTasks.AddPollTopic;
import com.eventplaner.tasks.pollTasks.CreatePoll;
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
public class PollController {

    /*
        Creating
     */
    @PostMapping("/poll/createPoll")
    public void createPoll(@RequestParam Map<String, String> params, HttpServletResponse response, Principal user) throws IOException {

        RegisteredUser organizer = (RegisteredUser) new GetUser(user.getName()).execute().get(0);

        boolean status = false;
        if(params.get("status").equals("on")){
            status = true;
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
