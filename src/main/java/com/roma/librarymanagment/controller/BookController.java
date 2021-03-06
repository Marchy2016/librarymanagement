package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.services.AuthorService;
import com.roma.librarymanagment.services.BookService;
import com.roma.librarymanagment.services.CategoryService;
import com.roma.librarymanagment.services.PublisherService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;
    private BookProsConfig bookProsConfig;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService, BookProsConfig bookProsConfig) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.bookProsConfig = bookProsConfig;
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
        Publisher publisher1 = publisherService.findPublisherById(publisher.getId());
        List<Book> books = bookService.findBooksByPublisherId(publisher1.getId());
        model.addAttribute("books", books);
        model.addAttribute("publisher",publisher1);
        return "publisherBooks";
    }
    @RequestMapping(path = "/author/books/{author}", method = RequestMethod.GET)
    private String listBooksByAuthor(Model model,@PathVariable("author") Author author){
        Author author1 = authorService.findAuthorById(author.getId());
        List<Book> books = bookService.findBooksByAuthorId(author1.getId());
        model.addAttribute("books", books);
        model.addAttribute("author", author);
        return "authorBooks";
    }

    @RequestMapping(path = "/savebook", method = RequestMethod.GET)
    private String saveBook(Model model){
        model.addAttribute("book",new Book());
        System.out.println("Authors :" + getAuthors().toString());
        List<Publisher> publishers = publisherService.listPublishers();
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", getAuthors());
        model.addAttribute("categories", getCategories());
        return "addBooks";
    }
    @RequestMapping(path = "/savebook", method = RequestMethod.POST)
    private String saveBook(Model model, String isbn, Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished) throws Exception {
        if(null != bookService.findByIsbn(isbn)) {
            throw new DuplicateKeyException("Book with isbn " + isbn + "already exists");
        }else{
            final Book book = bookService.add(isbn, publisher, author, title, category, edition, yearPublished);
            if(null != book){
                model.addAttribute("book", book);
                return bookProsConfig.getDisplayBook() + book.getId();
            }else{
                return "Error saving a book";
            }

        }
    }

    @RequestMapping(path = "/deleteBook/{id}", method = RequestMethod.GET)
    private String deleteBook(@PathVariable Long id){
        bookService.deleteById(id);
        return bookProsConfig.getDisplayBooks();
    }

    @RequestMapping(path = "/updateBook/{isbn}", method = RequestMethod.GET)
    private String getBook(Model model,@PathVariable String isbn){
        model.addAttribute("book", bookService.findByIsbn(isbn));
        System.out.println("Authors :" + getAuthors().toString());
        List<Publisher> publishers = publisherService.listPublishers();
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", getAuthors());
        model.addAttribute("categories", getCategories());
        return "updateBook";
    }

    @RequestMapping(path = "/updateBook/{isbn}", method = RequestMethod.POST)
    private String updateBook(Model model,@PathVariable String isbn,Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        Book book = bookService.updateBook(isbn,publisher,author,title, category,edition,yearPublished);
        model.addAttribute("book", book);
        return bookProsConfig.getDisplayBook() + book.getId();
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
        }
        return "book";
    }
    private List<Author> getAuthors(){
      return  authorService.findAll();
    }
    private List<Category> getCategories(){
        return  categoryService.listCategories();
    }

}
