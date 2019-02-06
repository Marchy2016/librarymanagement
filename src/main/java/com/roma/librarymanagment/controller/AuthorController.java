package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthorController {
    @Autowired
    public AuthorRepository authorRepository;

    @RequestMapping(path = "/listauthors", method = RequestMethod.GET)
    public String listAuthors(Model model){
        model.addAttribute("authors",authorRepository.findAll());
        return "authors";
    }

    @RequestMapping(path = "/saveauthor", method = RequestMethod.POST)
    private void saveBook( @RequestParam(value = "firstName", required = false) String firstName,
                           @RequestParam(value = "lastName", required = false) String lastName){

        Author book = new Author(firstName,lastName);
        authorRepository.save(book);


    }


}
