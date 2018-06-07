package com.eventplaner;

import com.eventplaner.model.*;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.tasks.commentTasks.AddComment;
import com.eventplaner.tasks.pollTasks.CreatePoll;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

@Controller
//@CrossOrigin(origins = {"http://localhost:8081"})
public class ViewController {

    @GetMapping("/")
    public String renderHome(Principal user, Model model){
        boolean loggedIn = false;
        if(user != null){
            loggedIn = true;
        }
        model.addAttribute("loggedIn", loggedIn);
        return "index";
    }

    @Autowired
    PollRepository pollRepository;


    @GetMapping("/poll")
    public String home(@RequestParam(value="poll", required = false) String pollId, Model model, Principal user){

        boolean loggedIn = false;
        boolean isOrganizer = false;
        if(user != null){
            loggedIn = true;
        }

        if(pollId == null){

            Iterable<Poll> polls = pollRepository.findAll();
            ArrayList<Poll> validPolls = new ArrayList<>();
            for(Poll poll:polls){
                if(poll.isPublic()){
                    validPolls.add(poll);
                }else{
                    if(loggedIn){
                        User logged_in_User = new GetUser(user.getName()).execute().get(0);



                        for(User participant: poll.getParticipants()){

                            if(participant.getEmail().equals(logged_in_User.getEmail())){
                                validPolls.add(poll);
                                break;
                            }
                        }
                        for(User organizer: poll.getOrganizers()){
                            if(organizer.getEmail().equals(logged_in_User.getEmail())){
                                validPolls.add(poll);
                                isOrganizer = true;
                                break;
                            }
                        }
                    }
                }
            }

            model.addAttribute("polls", validPolls /*new GetPoll().execute()*/);
            return "polls";

        }



        try{
            //PollRepositoryGiver giver = new PollRepositoryGiver();
            //PollRepository pollRepository = giver.getPollRepository();
            //Poll poll = new GetPoll(pollId).execute().get(0);
            Poll poll = pollRepository.findById(pollId);
            int i = poll.getCommentSystem().getComments().size();
            System.out.println(poll.getPollTopics().size());
            
            model.addAttribute("loggedIn", loggedIn);
            model.addAttribute("isOrganizer", isOrganizer);
            model.addAttribute("poll", poll);
            //model.addAttribute("comments", poll.getCommentSystem()/*.getComments()*/);
            /*model.addAttribute("name", poll.getName());
            model.addAttribute("id", poll.getId());*/
            return "poll";
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("polls", new GetPoll().execute());
            return "polls";
        }
    }

    @GetMapping("/test")
    public String seas(HttpServletRequest request, Model model, Principal user){

        boolean loggedIn = false;
        if(user != null){
            loggedIn = true;
        }
        model.addAttribute("loggedIn", loggedIn);

        return "test";
    }

    @GetMapping("/createpoll")
    public String createpoll(HttpServletRequest request, Model model, Principal user){
        boolean loggedIn = false;
        if(user != null){
            loggedIn = true;
        }
        model.addAttribute("loggedIn", loggedIn);
        return "createpoll";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Principal user){
        boolean loggedIn = false;
        if(user != null){
            loggedIn = true;
        }
        model.addAttribute("loggedIn", loggedIn);

        return "login";
    }

    @GetMapping("/signup")
    public String signup(HttpServletRequest request, Model model, Principal user){
        boolean loggedIn = false;
        if(user != null){
            loggedIn = true;
        }
        model.addAttribute("loggedIn", loggedIn);
        return "signup";
    }

}
