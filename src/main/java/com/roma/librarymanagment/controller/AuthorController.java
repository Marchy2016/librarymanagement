package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(path = "/authors", method = RequestMethod.GET)
    public String listAuthors(Model model){
        model.addAttribute("authors",authorService.findAll());
        return "authors.html";
    }
    @RequestMapping(path = "/saveauthors", method = RequestMethod.GET)
    private String saveAuthor(Model model){

      model.addAttribute("author",new Author());
        return "addAuthors";
    }

    @RequestMapping(path = "/saveauthors", method = RequestMethod.POST)
    private String saveAuthor(Model model,@ModelAttribute Author author){

        final Author author_ = authorService.add(author.getFirstName(),author.getLastName(),author.getEmail());
        model.addAttribute("authors",new ArrayList<Author>(){{add(author_);}});
        return "menu";
    }




}
