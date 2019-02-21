package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.services.AuthorService;
import com.roma.librarymanagment.services.BookService;
import com.roma.librarymanagment.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping({"","/","/menu","/menu.html"})
    private String index(){
        return "menu";
    }


    @RequestMapping(path = "/books", method = RequestMethod.GET)
    private String listBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "books";

    }

    @RequestMapping(path = "/savebook", method = RequestMethod.GET)
    private String saveBook(Model model){
        model.addAttribute("book",new Book());
        List<Author>  authors = authorService.findAll();
        System.out.println("Authors :" + authors.toString());
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        return "addBooks";
    }


    @RequestMapping(path = "/savebook", method = RequestMethod.POST)
    private String saveBook(Model model,String isbn, String title, Publisher publisherId,Author authorId){
       /* List<Author>  authors = authorService.findAll();
        System.out.println("Authors :" + authors.toString());
        List<Publisher> publishers = publisherService.findAll();
       /* ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("publishers", publishers);*/
        // modelAndView.addObject("books", new ArrayList<Book>(){{add(book);}});
        final Book book = bookService.add(title,isbn,publisherId,authorId);
        model.addAttribute("book", book);
       // model.addAttribute("books",new ArrayList<Book>(){{add(book);}});
        //model.addAttribute("publishers", publishers);
       // model.addAttribute("authors", authors);

        return "menu";
    }

}
