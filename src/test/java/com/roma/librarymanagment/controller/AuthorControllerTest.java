package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveAuthor() throws Exception {
       String name = "marcus";
       String surname = "king";
       String email = "marcusking@gmail.com";
       Author author = authorService.add(name,surname,email);
       assertNotNull(author);
       assertThat(author.getEmail(), is("marcusking@gmail.com"));
       mockMvc.perform(post("/saveauthors"))
                .andExpect(status().isOk())
                .andExpect(view().name("author"));
    }
    @Test
    public void deleteAuthor() {
        Author author = authorService.findAuthorByEmail("marcusking@gmail.com");
        assertNotNull(author);
        authorService.deleteAuthor(author.getId());
        assertNotNull(author.getId());
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listAuthors() throws Exception {
        List<Author> authors = authorService.findAll();
        assertNotNull(authors);
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors.html"))
                .andExpect(model().attribute("authors", hasSize(7)));

    }
}