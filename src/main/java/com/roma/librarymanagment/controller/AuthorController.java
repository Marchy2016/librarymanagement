package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthorController {

    private AuthorService authorService;
    private  Author author;
    private BookProsConfig bookProsConfig;
    private final String AUTHOR_VIEW = "author";

    public AuthorController(AuthorService authorService, BookProsConfig bookProsConfig) {
        this.authorService = authorService;
        this.bookProsConfig = bookProsConfig;
    }

    @RequestMapping(path = "/authors", method = RequestMethod.GET)
    public String listAuthors(Model model){
        model.addAttribute("authors",authorService.findAll());
        return "authors.html";
    }
    @RequestMapping(path = "/saveauthors", method = RequestMethod.GET)
    private String saveAuthor(Model model){

      model.addAttribute(AUTHOR_VIEW,new Author());
        return "addAuthors";
    }

    @RequestMapping(path = "/saveauthors", method = RequestMethod.POST)
    private String saveAuthor(Model model,@Valid @ModelAttribute(AUTHOR_VIEW) Author author){

      this.author = authorService.add(author.getFirstName(),author.getLastName(),author.getEmail());
        model.addAttribute(AUTHOR_VIEW,this.author);
        return AUTHOR_VIEW;
    }

    @RequestMapping(path = "/updateauthors", method = RequestMethod.POST)
    private String updateAuthor(@ModelAttribute Author authr){
        if(authr != null){
            author = authorService.updateAuthor(authr.getId(),authr.getFirstName(),authr.getLastName(),authr.getEmail());
        }
        return AUTHOR_VIEW;
    }


    @RequestMapping(path = "/findAuthorById/{id}", method = RequestMethod.GET)
    private String findAuthorById(Model model,@PathVariable Long id){
        author = authorService.findAuthorById(id);
            if(author != null){
                model.addAttribute(AUTHOR_VIEW, author);
            }
        return "updateAuthor";
    }

    @RequestMapping(path = "/deleteauthor/{id}", method = RequestMethod.GET)
    private String deleteAuthor(Model model,@PathVariable Long id){
        authorService.deleteAuthor(id);
      return bookProsConfig.getDisplayAuthors();
    }






}
