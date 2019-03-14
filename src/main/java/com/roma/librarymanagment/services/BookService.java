package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface BookService{
    List<Book> findAll();
    void deleteById(Long id);
    Book updateBook(String isbn,Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished);
    Book findByIsbn(String isbn);
    Book findBookByTitle(String title);
    List<Book> findBooksByPublisherId(Long id);
    List<Book> findBooksByAuthorId(Long id);

    Book add(String isbn, Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished);

}
