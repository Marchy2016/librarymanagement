package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.services.AuthorService;
import com.roma.librarymanagment.services.BookService;
import com.roma.librarymanagment.services.CategoryService;
import com.roma.librarymanagment.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/menu","/menu.html"})
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
        List<Category> categories = categoryService.findAll();
        System.out.println("Authors :" + authors.toString());
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        return "addBooks";
    }

    @RequestMapping(path = "/deleteBook/{id}", method = RequestMethod.GET)
    private String deleteBook(@PathVariable Long id){
        bookService.deleteById(id);
        return "menu";
    }

    @RequestMapping(path = "/updateBook/{isbn}", method = RequestMethod.GET)
    private String getBook(Model model,@PathVariable String isbn){
        model.addAttribute("book", bookService.findByIsbn(isbn));
        List<Author>  authors = authorService.findAll();
        List<Category> categories = categoryService.findAll();
        System.out.println("Authors :" + authors.toString());
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        return "updateBook";
    }

    @RequestMapping(path = "/updateBook/{isbn}", method = RequestMethod.POST)
    private String updateBook(@PathVariable String isbn,Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        bookService.updateBook(isbn,publisher,author,title, category,edition,yearPublished);
        return "menu";
    }





    @RequestMapping(path = "/savebook", method = RequestMethod.POST)
    private String saveBook(Model model, String isbn, Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        final Book book = bookService.add(isbn,publisher,author,title, category,edition,yearPublished);
        model.addAttribute("book", book);
       return "menu";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    private String getIndex(){
        return "index";
    }

}
