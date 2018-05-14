package com.eventplaner;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class ViewController {

        @GetMapping("/")
        public String home(){

            return "index";
        }

        @GetMapping("/test")
        public String seas(HttpServletRequest request, Model model){
            return "test";
        }
}
