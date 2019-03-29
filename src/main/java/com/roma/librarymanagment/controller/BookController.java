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
import java.util.ArrayList;
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
    @RequestMapping(path = "/publisher/books/{publisher}", method = RequestMethod.GET)
    private String listBooksByPublisher(Model model,@PathVariable("publisher") Publisher publisher){
        Publisher publisher1 = publisherService.findById(publisher.getId());
        List<Book> books = bookService.findBooksByPublisherId(publisher1.getId());
        model.addAttribute("books", books);
        model.addAttribute("publisher",publisher1);
        return "publisherBooks";
    }
    @RequestMapping(path = "/author/books/{author}", method = RequestMethod.GET)
    private String listBooksByAuthor(Model model,@PathVariable("author") Author author){
        Author author1 = authorService.findById(author.getId());
        List<Book> books = bookService.findBooksByAuthorId(author1.getId());
        model.addAttribute("books", books);
        model.addAttribute("author", author);
        return "authorBooks";
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
    @RequestMapping(path = "/savebook", method = RequestMethod.POST)
    private String saveBook(Model model, String isbn, Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        final Book book = bookService.add(isbn,publisher,author,title, category,edition,yearPublished);
        model.addAttribute("book", book);
        return "redirect:/searchBookById/" + book.getId();
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
    private String updateBook(Model model,@PathVariable String isbn,Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        Book book = bookService.updateBook(isbn,publisher,author,title, category,edition,yearPublished);
        model.addAttribute("book", book);
        return "redirect:/searchBookById/" + book.getId();
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    private String getIndex(){
        return "index";
    }

    @RequestMapping(path = "/searchBook/{title}", method = RequestMethod.GET)
    private String getBookByTitle(Model model,@PathVariable String title){
        Book book = bookService.findBookByTitle(title);
        if(book != null){
            model.addAttribute("book", book);
        }
        return "book";
    }

    @RequestMapping(path = "/searchBookById/{id}", method = RequestMethod.GET)
    private String getBookById(Model model,@PathVariable Long id){
        Book book = bookService.findBookById(id);
        if(book != null){
            model.addAttribute("book", book);
            System.out.println("Book" + book);
        }
        return "book";
    }

}
