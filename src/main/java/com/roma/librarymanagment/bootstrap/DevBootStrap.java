package com.roma.librarymanagment.bootstrap;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.AuthorRepository;
import com.roma.librarymanagment.repositories.BookRepository;
import com.roma.librarymanagment.repositories.PublisherRepository;
import lombok.Data;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Data
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
       // initData();

    }
   /* private void initData(){


        Publisher publisher = new Publisher();
        publisher.setName("Snowable");
        publisher.setAddress("401 Foxer");
        publisherRepository.save(publisher);

        Author marcus = new Author("Marcus","Tema");


        Book ddd = new Book("Domain Driven Design","1234",publisher);
        marcus.getBooks().add(ddd);
        ddd.getAuthors().add(marcus);
        authorRepository.save(marcus);
        bookRepository.save(ddd);


        Publisher publisher1 = new Publisher();
        publisher.setName("Valdy");
        publisher.setAddress("401 Foxer");
        publisherRepository.save(publisher1);
        Author eric = new Author("Eric","King");
        Book noEjb = new Book("J2EE Development","222",publisher1);
        eric.getBooks().add(noEjb);
        noEjb.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(noEjb);


        Publisher publisher2 = new Publisher();
        publisher.setName("Thuli");
        publisher.setAddress("401 Foxer");
        publisherRepository.save(publisher2);
        Author molefe = new Author("Molefe","Emial");
        Book bible = new Book("Bible book","362",publisher);
        eric.getBooks().add(bible);
        bible.getAuthors().add(molefe);
        authorRepository.save(molefe);
        bookRepository.save(bible);

    }*/

}
