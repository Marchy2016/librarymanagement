package com.roma.librarymanagment.config;

import com.roma.librarymanagment.repositories.AuthorRepository;
import com.roma.librarymanagment.repositories.BookRepository;
import com.roma.librarymanagment.repositories.PublisherRepository;
import com.roma.librarymanagment.services.BookServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceConfig {

    @Bean
    BookServiceFactory bookServiceFactory(BookRepository bookRepository,PublisherRepository publisherRepository, AuthorRepository authorRepository){
        return new BookServiceFactory(bookRepository, publisherRepository, authorRepository);
    }


}
