package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AuthorController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = BookProsConfig.class)
@ComponentScan("com.roma.librarymanagment")
public class AuthorControllerTest {

    @Mock
    @Autowired
    private AuthorService authorService;
    @Mock
    private AuthorController controller;
    private MockMvc mockMvc;
    private List<Author> authors;
    @Autowired
    private BookProsConfig bookProsConfig;

    @Test
    public void saveAuthor() throws Exception {
       String name = "marcus";
       String surname = "king";
       String email = "king@gmail.com";
       Author author = authorService.add(name,surname,email);
      // assertNotNull(author);
       when(authorService.findAuthorById(author.getId())).thenReturn(author);
      // assertThat(author.getEmail(), is("king@gmail.com"));
        mockMvc.perform(post("/saveauthors"))
                .andExpect(status().isOk())
                .andExpect(view().name("author"));
    }

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        //controller = new AuthorController(authorService,bookProsConfig);

        authors = authorService.findAll();
        this.mockMvc = MockMvcBuilders
                  .standaloneSetup(controller)
                  .build();
    }

    @Test
    public void listAuthors() throws Exception {
        authors = authorService.findAll();
        when(authorService.findAll()).thenReturn(authors);
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors.html"))
                .andExpect(model().attribute("authors", hasSize(0)));

    }
}