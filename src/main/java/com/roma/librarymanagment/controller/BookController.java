package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import com.roma.librarymanagment.services.BookService;
import com.roma.librarymanagment.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
import java.util.Set;

@Controller
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    private BookService bookService;

    @RequestMapping(path = "/listbooks", method = RequestMethod.GET)
    private String listBooks(Model model){

        model.addAttribute("libBooks", bookRepository.findAll());
        return "books";

    }
    @RequestMapping(path = "/listbooks/{id}", method = RequestMethod.GET)
    private Optional<Book> findBooksById(Long id){
        return bookRepository.findById(id);

    }

    @RequestMapping(path = "/savebook", method = RequestMethod.POST)
    private void saveBook(String isbn, String title, Publisher publisher, Set<Author> author){
        bookService.add(title,isbn,publisher,author);
    }

}
