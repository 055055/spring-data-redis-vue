package com.example.vue.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController implements ErrorController {

    @GetMapping({"/","/error"})
    public String goHome(){
        System.out.println("!!");
        return "index.html";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
