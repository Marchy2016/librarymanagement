package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Role;
import com.roma.librarymanagment.model.User;
import com.roma.librarymanagment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = {"/signup","/signup.html"}, method = RequestMethod.POST)
    public String saveUser(Model model, String name, String username, String email, String password, Set<Role> userRoles){
       User user = userService.saveUser(name,username,email,password,userRoles);
        model.addAttribute("user", user);
        return "menu";
    }

    @RequestMapping(path = {"/signup","/signup.html"}, method = RequestMethod.GET)
    public String saveUser(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }


    @RequestMapping(path = {"","/", "/login","/login.html"})
    public String longIn(){
        return "login";
    }
    @RequestMapping(path = {"/signup","/signup.html"})
    public String signUp(){
        return "signup";
    }
}
