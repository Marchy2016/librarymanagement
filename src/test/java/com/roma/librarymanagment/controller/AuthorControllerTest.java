package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import com.roma.librarymanagment.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Mock
    AuthorRepository authorRepository;
    @Autowired
    private MockMvc mockMvc;
    private static final String email = "marcusking@gmail.com";
    private static final  String name = "marcus";
    private static final String surname = "king";

    @Test
    public void saveAuthor() throws Exception {
       Author author = new Author();
       author.setEmail(email);
       author.setFirstName(name);
       author.setLastName(surname);
        author.setId(1l);

       assertNotNull(author);
       when(authorRepository.save(author)).thenReturn(author);
       Author author1 = authorRepository.save(author);
       assertThat(author1.getEmail(), is("marcusking@gmail.com"));
       verify(authorRepository, times(1)).save(author);

        mockMvc.perform(get("/saveauthors"))
                .andExpect(status().isOk())
                .andExpect(view().name("addAuthors"));
    }
    @Test
    public void deleteAuthor() {

        Author author = new Author();
        author.setEmail(email);
        author.setFirstName(name);
        author.setLastName(surname);
        author.setId(1l);

        when(authorRepository.findAuthorByEmail("marcusking@gmail.com")).thenReturn(java.util.Optional.ofNullable(author));
        assertNotNull(author.getId());
        authorRepository.delete(author);
        assertNotNull(author.getId());
        verify(authorRepository, times(1)).delete(author);
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listAuthors() throws Exception {

        Author author = new Author();
        author.setEmail(email);
        author.setFirstName(name);
        author.setLastName(surname);

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("authors"))
                .andExpect(view().name("authors.html"));

        assertNotNull(authors);
        when(authorRepository.findAll()).thenReturn(authors);
        assertEquals(authorRepository.findAll(),authors);
        verify(authorRepository, times(1)).findAll();




    }
}