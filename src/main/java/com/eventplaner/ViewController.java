package com.eventplaner;

import com.eventplaner.model.Comment;
import com.eventplaner.model.Poll;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
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

@Controller
public class ViewController {

        @GetMapping("/")
        public String renderHome(){
            return "index";
        }

        @GetMapping("/poll")
        public String home(@RequestParam(value="poll", required = false) String pollId, Model model){

            if(pollId == null){
                return "polls";

            }

            new CreateUser("admin@admin.com", "admin", "admin").execute();
            RegisteredUser admin = (RegisteredUser) new GetUser("admin@admin.com").execute().get(0);
            new CreatePoll(admin, "new Poll", "new Poll", true).execute();



            pollId = new GetPoll().execute()
                    .get(0)
                    .getId();

            Poll poll = new GetPoll(pollId).execute().get(0);

            new AddComment(poll.getCommentSystem(), admin, "seas").execute();

            poll = new GetPoll(pollId).execute().get(0);

            Comment c = new Comment(admin, "comment");
            Comment c2 = new Comment(admin, "subcomment");
            Comment c3 = new Comment(admin, "subsubcomment");
            Comment c4 = new Comment(admin, "subsubsubcomment");


            c3.getSubCommentSystem().addComment(c4);
            c2.getSubCommentSystem().addComment(c3);
            c.getSubCommentSystem().addComment(c2);

            poll.getCommentSystem().addComment(c);


            System.out.println("asdasdasdasdasddadasdasdasdsd: "+poll.getCommentSystem().getComments().size());
            try{
                model.addAttribute("poll", poll.getName());
                model.addAttribute("comments", poll.getCommentSystem()/*.getComments()*/);
                /*model.addAttribute("name", poll.getName());
                model.addAttribute("id", poll.getId());*/
            }catch(Exception e){
                return "polls";
            }

            return "poll";
        }

        @GetMapping("/test")
        public String seas(HttpServletRequest request, Model model){
            return "test";
        }

        @GetMapping("/createpoll")
        public String createpoll(HttpServletRequest request, Model model){
                    return "createpoll";
        }

        @GetMapping("/login")
        public String login(HttpServletRequest request, Model model){
            return "login";
        }

        @GetMapping("/signup")
        public String signup(HttpServletRequest request, Model model){

            return "signup";
        }

}
