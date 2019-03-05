package com.roma.librarymanagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping(path = {"","/", "/login","/login.html"})
    public String longIn(){
        return "login";
    }
    @RequestMapping(path = {"/signup","/signup.html"})
    public String signUp(){
        return "signup";
    }
}
