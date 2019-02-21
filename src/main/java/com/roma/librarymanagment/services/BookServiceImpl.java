package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(String title, String isbn, Publisher publisher, Author author){

        Book ddd = new Book(title,isbn,publisher,author);
        bookRepository.save(ddd);

        return ddd;
    }
    public List<Book> findAll(){
        return  bookRepository.findAll();
    }
    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }
}
