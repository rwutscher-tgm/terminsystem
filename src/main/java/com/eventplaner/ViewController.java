package com.eventplaner;

import com.eventplaner.model.*;
import com.eventplaner.tasks.commentTasks.AddComment;
import com.eventplaner.tasks.pollTasks.CreatePoll;
import com.eventplaner.tasks.pollTasks.GetPoll;
import com.eventplaner.tasks.userTasks.CreateUser;
import com.eventplaner.tasks.userTasks.GetUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
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

        @GetMapping("/poll")
        public String home(@RequestParam(value="poll", required = false) String pollId, Model model, Principal user){

            if(pollId == null){
                model.addAttribute("polls", new GetPoll().execute());
                return "polls";

            }

            //new CreateUser("admin@admin.com", "admin", "admin").execute();
            RegisteredUser admin = new RegisteredUser("admin@admin.com", "admin", "admin");//(RegisteredUser) new GetUser("admin@admin.com").execute().get(0);
            //new CreatePoll(admin, "new Poll", "new Poll", true).execute();



            pollId = new GetPoll().execute()
                    .get(0)
                    .getId();

            //Poll poll = new GetPoll(pollId).execute().get(0);

            //new AddComment(poll.getCommentSystem(), admin, "seas").execute();

            //poll = new GetPoll(pollId).execute().get(0);

            Poll poll = new Poll(admin, "Konzert der flying Borkos", "wir wollen ein Konzert planen und wollen wissen wann ihr Zeit hättet.", true);

            Comment c = new Comment(admin, "comment");
            Comment c2 = new Comment(admin, "subcomment");
            Comment c3 = new Comment(admin, "subsubcomment");
            Comment c4 = new Comment(admin, "subsubsubcomment");


            c3.getSubCommentSystem().addComment(c4);
            c2.getSubCommentSystem().addComment(c3);
            c.getSubCommentSystem().addComment(c2);

            poll.getCommentSystem().addComment(c);

            poll.addPollTopic(new PollTopic("13.12.2018"));
            poll.addPollTopic(new PollTopic("18.9.2018"));
            poll.addPollTopic(new PollTopic("1.7.2018"));


            System.out.println("asdasdasdasdasddadasdasdasdsd: "+poll.getCommentSystem().getComments().size());

            boolean loggedIn = false;
            if(user != null){
                loggedIn = true;
            }

            try{
                model.addAttribute("loggedIn", loggedIn);
                model.addAttribute("poll", poll);
                //model.addAttribute("comments", poll.getCommentSystem()/*.getComments()*/);
                /*model.addAttribute("name", poll.getName());
                model.addAttribute("id", poll.getId());*/
            }catch(Exception e){
                model.addAttribute("polls", new GetPoll().execute());
                return "polls";
            }

            return "poll";
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
