package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorController {

    private AuthorService authorService;
    private  Author author;
    private BookProsConfig bookProsConfig;

    @Autowired
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

      model.addAttribute("author",new Author());
        return "addAuthors";
    }

    @RequestMapping(path = "/saveauthors", method = RequestMethod.POST)
    private String saveAuthor(Model model,@ModelAttribute Author author) throws Exception {
        if(null != authorService.findAuthorById(author.getId())) {
            throw new DuplicateKeyException("Author already exists");
        }else{
            final Author author_ = authorService.add(author.getFirstName(),author.getLastName(),author.getEmail());
            model.addAttribute("author",author_);
            return "author";
        }
    }

    @RequestMapping(path = "/updateauthors", method = RequestMethod.POST)
    private String updateAuthor(@ModelAttribute Author author){
        if(author != null){
            this.author = authorService.updateAuthor(author.getId(),author.getFirstName(),author.getLastName(),author.getEmail());
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
      return bookProsConfig.getDisplayAuthors();
    }






}
