package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService{
    List<Book> findAll();
    Book add(String title, String isbn, Publisher publisher, Author author);

}
