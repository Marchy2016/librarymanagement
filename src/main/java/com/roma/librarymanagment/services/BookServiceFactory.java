package com.roma.librarymanagment.services;

import com.roma.librarymanagment.repositories.AuthorRepository;
import com.roma.librarymanagment.repositories.BookRepository;
import com.roma.librarymanagment.repositories.PublisherRepository;

public class BookServiceFactory {

    BookRepository bookRepository;
    PublisherRepository publisherRepository;
    AuthorRepository authorRepository;


    public BookServiceFactory(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }
}
