package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Book;
import com.roma.librarymanagment.model.Category;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration
public class BookServiceImplTest {


    public static final String HOW_TO_PROGRAM = "How to program";
    @InjectMocks
    BookServiceImpl bookService;
    @Mock
    BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void add() {
        Book book = new Book("HTP001",new Publisher(),new Author(), HOW_TO_PROGRAM,new Category(),"2nd Edition",new Date(20/02/2018));
        when(bookRepository.save(book)).thenReturn(book);
        Book book1 = bookRepository.save(book);
        assertEquals(book.getId(), book1.getId());
        verify(bookRepository).save(any());
    }

    @Test
    public void findAll() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> books = bookService.findAll();
        assertEquals(3, books.size());
        verify(bookRepository).findAll();

    }

    @Test
    public void findBookByTitle() {

        Book book = new Book("HTP001",new Publisher(),new Author(), HOW_TO_PROGRAM,new Category(),"2nd Edition",new Date(20/02/2018));
        when(bookRepository.findBookByTitle(HOW_TO_PROGRAM)).thenReturn(book);
        assertEquals(bookService.findBookByTitle(HOW_TO_PROGRAM),book);
        assertEquals(HOW_TO_PROGRAM,book.getTitle());
        verify(bookRepository).findBookByTitle(any());

    }
}