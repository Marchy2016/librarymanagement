package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface BookService {
    Book add(String title, String isbn, Publisher publisher, Set<Author> authors);
    List<Book> findAll();
    Optional<Book> findById(Long id);
}
