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
    private  Author author;

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
        model.addAttribute("author",author_);
        return "author";
    }

    @RequestMapping(path = "/updateauthors", method = RequestMethod.POST)
    private String updateAuthor(@ModelAttribute Author authr){
        if(authr != null){
            author = authorService.updateAuthor(authr.getId(),authr.getFirstName(),authr.getLastName(),authr.getEmail());
        }
        return "author";
    }


    @RequestMapping(path = "/findAuthorById/{id}", method = RequestMethod.GET)
    private String findAuthorById(Model model,@PathVariable Long id){
        author = authorService.findAuthorById(id);
            if(author != null){
                model.addAttribute("author", author);
            }
        return "updateAuthor";
    }

    @RequestMapping(path = "/deleteauthor/{id}", method = RequestMethod.GET)
    private String deleteAuthor(Model model,@PathVariable Long id){
        authorService.deleteAuthor(id);
      return "redirect:/authors";
    }






}
