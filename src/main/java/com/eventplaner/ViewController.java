package com.eventplaner;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

        @GetMapping("/")
        public String renderHome(){
            return "index";
        }

        @GetMapping("/poll")
        public String home(HttpServletRequest request, Model model){

            if(request.getAttribute("poll") == null){
                return "poll";
            }
            String pollId = (String) request.getAttribute("poll");



            model.addAttribute("pollName",pollId);

            return "poll";
        }

        @GetMapping("/test")
        public String seas(HttpServletRequest request, Model model){
            return "test";
        }


}
