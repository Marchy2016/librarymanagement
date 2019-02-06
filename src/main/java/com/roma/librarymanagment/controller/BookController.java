package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
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

    @RequestMapping(path = "/listbooks", method = RequestMethod.GET)
    private String listBooks(Model model){

        model.addAttribute("libBooks", bookRepository.findAll());
        return "books";

    }
    @RequestMapping(path = "/listbooks/{id}", method = RequestMethod.GET)
    private Optional<Book> findBooksById(Long id){
        return bookRepository.findById(id);

    }

    @RequestMapping(path = "/savebook/{title},{isbn},{publisher},{author}", method = RequestMethod.POST)
    private void saveBook(String isbn, String title, Publisher publisher, Set<Author> author){
        Book book = new Book(isbn,title,publisher,author);
        bookRepository.save(book);


    }

}
