package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import lombok.extern.slf4j.XSlf4j;
import org.dozer.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@XSlf4j
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private Book book;
    private Mapper mapper;
    private EntityManager em;

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
    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

   /* private Author mapAuthor(Author author){
        if(author != null){
            Author author = new Author();
            mapper.map(author,Author.class);
            return author;
      }
      return null;
    }*/
    private Publisher mapPublisher(Publisher publisher){
        if(publisher != null){
            Publisher publisherEntity = new Publisher();
            mapper.map(publisher,Publisher.class);
            return publisherEntity;
        }
        return null;
    }
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
    public  Book updateBook(String isbn,Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished){
        Book book = findByIsbn(isbn);
      //  if(book.getTitle() != title || book.getAuthor() != author || book.getCategory() != category || book.getEdition() != edition || book.getYearPublished() != yearPublished ){
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
    public  List<Book> findBooksByPublisher(Long id){
      List<Book> books = bookRepository.findBooksByPublisher(id);
        if(books != null){
            return books;
        }
        return null;
    }
}
