package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;


@XSlf4j
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(String isbn, Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null) {
            throw log.throwing(new DuplicateKeyException("This book already exists."));
        } else {
            if (title != null && !StringUtils.isEmpty(title) && isbn != null && !StringUtils.isEmpty(isbn) && publisher != null && author != null
                    && category != null && (edition != null && !StringUtils.isEmpty(edition)) && yearPublished != null) {
                book = new Book(isbn, publisher, author,title, category, edition, yearPublished);
                bookRepository.save(book);
            }
            return book;
        }
    }
    public List<Book> findAll(){
        return  bookRepository.findAll();
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
    public  Book updateBook(String isbn,Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        Book book = findByIsbn(isbn);
            book.setIsbn(isbn);
            book.setPublisher(publisher);
            book.setAuthor(author);
            book.setTitle(title);
            book.setCategory(category);
            book.setEdition(edition);
            book.setYearPublished(yearPublished);
            bookRepository.save(book);

        return book;
    }
    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }
    public Book findBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }
    @Override
    public  List<Book> findBooksByPublisherId(Long id){
        List<Book> books = bookRepository.findAll();
        List<Long> bookIds = new ArrayList<>();
        for(Book book : books){
            bookIds.add(book.getPublisher().getId());
        }
       return bookRepository.findBooksByPublisherId(id,bookIds);
    }
    public  Book findBookById(Long id){
        return bookRepository.findBookById(id);
    }

    @Override
    public  List<Book> findBooksByAuthorId(Long id){
        List<Book> books = bookRepository.findAll();
        List<Long> bookAuthorIds = new ArrayList<>();
        for(Book authorBook : books){
            bookAuthorIds.add(authorBook.getAuthor().getId());
        }
        return bookRepository.findBooksByAuthorId(id,bookAuthorIds);
    }




}
