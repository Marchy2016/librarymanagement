package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private Book book;
    private Mapper mapper;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(String title, String isbn, Publisher publisher, Author author) {
        if (title != null && !org.springframework.util.StringUtils.isEmpty(title) && isbn != null && !org.springframework.util.StringUtils.isEmpty(isbn) && publisher != null && author != null) {
            book = new Book(title, isbn,publisher, author);
            bookRepository.save(book);
        }
        return book;
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
}
