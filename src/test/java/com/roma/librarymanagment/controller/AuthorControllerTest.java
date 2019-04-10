package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AuthorController.class)
@EnableAutoConfiguration
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;
    private AuthorController controller;
    private MockMvc mockMvc;
    private List<Author> authors;
    private BookProsConfig bookProsConfig;

    @Test
    public void saveAuthor() throws Exception {
        Author author = new Author();
        when(authorService.add("","","")).thenReturn(author);
        mockMvc.perform(post("/saveauthors"))
                .andExpect(status().isOk())
                .andExpect(view().name("author"));
    }

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        controller = new AuthorController(authorService,bookProsConfig);

        authors = new ArrayList<>();
        Author author = new Author();
        Author author1 = new Author();
        Author author2 = new Author();

        authors.add(author);
        authors.add(author1);
        authors.add(author2);

        this.mockMvc = MockMvcBuilders
                  .standaloneSetup(controller)
                  .build();
    }

    @Test
    public void listAuthors() throws Exception {
        when(authorService.findAll()).thenReturn(authors);
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors.html"))
                .andExpect(model().attribute("authors", hasSize(3)));

    }
}