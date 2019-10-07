package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


import java.util.*;


@XSlf4j
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private Book book;
    private static Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(String isbn, Publisher publisher, Author author, String title, Category category, String edition, Date yearPublished) {
        book = bookRepository.findByIsbn(isbn).isPresent() ? bookRepository.findByIsbn(isbn).get() : null;
        if (ObjectUtils.isNotEmpty(book)) {
            throw log.throwing(new DuplicateKeyException("This book {} already exists."));
        } else {
            if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(isbn) && ObjectUtils.isNotEmpty(publisher) && ObjectUtils.isNotEmpty(author)
                    && (ObjectUtils.isNotEmpty(category)) && StringUtils.isNotEmpty(edition) && yearPublished != null) {
                try {
                    book = new Book(isbn, publisher, author, title, category, edition, yearPublished);
                    bookRepository.save(book);
                } catch (Exception ex) {
                    logger.error("Error saving a book with title: {}", title);
                    logger.error(Arrays.toString(ex.getStackTrace()));
                }
            }
            return book;
        }
    }

    public List<Book> findAll() {
        return bookRepository.findAll().stream().findFirst().isPresent() ? bookRepository.findAll() : null;
    }

    public void deleteById(Long id) {
        if(id != null){
            try{
                book = bookRepository.findBookById(id);
                if(ObjectUtils.isNotEmpty(book)){
                    bookRepository.deleteById(book.getId());
                }

            }catch (NullPointerException ex){
                logger.error("Error find the book with id : {}",id);
            }catch (Exception ex){
                logger.error("Error deleting book with id : {}",id);
                logger.error("Error: {}",ex.getMessage());
            }
        }

    }

    public Book updateBook(String isbn, Publisher publisher, Author author, String title, Category category, String edition, Date yearPublished) {
        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(isbn) && ObjectUtils.isNotEmpty(publisher) && ObjectUtils.isNotEmpty(author)
                && ObjectUtils.isNotEmpty(category) && StringUtils.isNotEmpty(edition) && yearPublished != null) {
            try {
                book = findByIsbn(isbn);
                if (ObjectUtils.isNotEmpty(book)) {
                    book.setIsbn(isbn);
                    book.setPublisher(publisher);
                    book.setAuthor(author);
                    book.setTitle(title);
                    book.setCategory(category);
                    book.setEdition(edition);
                    book.setYearPublished(yearPublished);
                    bookRepository.save(book);
                }
            } catch (NullPointerException ex) {
                logger.error("A book with title : {} could not be found", title);

            } catch (Exception ex) {
                logger.error("Error encounter while saving the book with title :{}", title);
            }
        }
        return book;
    }

    public Book findByIsbn(String isbn) {
        if (StringUtils.isNotEmpty(isbn)) {
            try {
                book = bookRepository.findByIsbn(isbn).isPresent() ? bookRepository.findByIsbn(isbn).get() : null;
            } catch (NullPointerException ex) {
                logger.error("A book with ISBN : {} could not be found.", isbn);
            }
        }
        return book;
    }

    public Book findBookByTitle(String title) {
        if (StringUtils.isNotEmpty(title)) {
            try {
                book = bookRepository.findBookByTitle(title);
            } catch (NullPointerException ex) {
                logger.error("A book with title : {} could not be found.", title);
            }
        }
        return book;
    }

    @Override
    public List<Book> findBooksByPublisherId(Long id) {
        List<Book> books = bookRepository.findAll();
        List<Book> publisherBooks = null;
        List<Long> bookIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(books)) {
            for (Book book : books) {
                bookIds.add(book.getPublisher().getId());
            }
            try {
                publisherBooks = bookRepository.findBooksByPublisherId(id, bookIds);
            } catch (Exception ex) {
                logger.error("Error finding books for publisher with id : {}", id);
                logger.error("Error :{}", ex.getMessage());
            }
        }
        return publisherBooks;
    }

    public Book findBookById(Long id) {
        if (id != null) {
            try {
                book = bookRepository.findBookById(id);
            } catch (NullPointerException e) {
                logger.error("A book with id : {} could not be found.", id);
            }
        }
        return book;
    }

    @Override
    public List<Book> findBooksByAuthorId(Long id) {
        List<Book> authorBooks = null;
        if (id != null) {
            List<Book> books = bookRepository.findAll();

            List<Long> bookAuthorIds = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(books)) {
                for (Book authorBook : books) {
                    bookAuthorIds.add(authorBook.getAuthor().getId());
                }
                try {
                    authorBooks = bookRepository.findBooksByAuthorId(id, bookAuthorIds);
                } catch (Exception ex) {
                    logger.error("Error find books for author with id: {}", id);
                }
            }

        }
        return authorBooks;

    }

}
