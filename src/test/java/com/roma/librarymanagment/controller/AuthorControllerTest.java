package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.Exceptions.NotFoundException;
import com.roma.librarymanagment.dtos.AuthorDTO;
import com.roma.librarymanagment.mapper.AuthorMapper;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import com.roma.librarymanagment.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Mock
    AuthorService authorService;
    @InjectMocks
    AuthorController authorController;

    @Autowired
    AuthorMapper authorMapper;


    private MockMvc mockMvc;

    private static final String email = "marcusking@gmail.com";
    private static final String name = "marcus";
    private static final String surname = "king";

    @Test
    public void saveAuthor() throws Exception {
        Author author = new Author();
        author.setEmail(email);
        author.setFirstName(name);
        author.setLastName(surname);
        author.setId(1l);

        assertNotNull(author);
        AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(author);
        when(authorService.add(authorDTO)).thenReturn(authorDTO);
    }

    @Test
    public void deleteAuthor() {

        Author author = new Author();
        author.setEmail(email);
        author.setFirstName(name);
        author.setLastName(surname);
        author.setId(1l);

        when(authorService.findAuthorByEmail("marcusking@gmail.com")).thenReturn(null);
        assertNotNull(author.getId());
        authorService.deleteAuthor(author.getId());
        assertNotNull(author.getId());
        verify(authorService, times(1)).deleteAuthor(author.getId());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void listAuthors() throws Exception {

        AuthorDTO author = new AuthorDTO();
        author.setEmail(email);
        author.setFirstName(name);
        author.setLastName(surname);

        List<AuthorDTO> authors = new ArrayList<>();
        authors.add(author);

        when(authorService.findAll()).thenReturn(authors);
        mockMvc.perform(get(AuthorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void notFoundExcetion() throws Exception {
        when(authorService.findAuthorById(anyLong())).thenThrow(NotFoundException.class);
    }

    @Test
    public void numberFormatExcetion() throws Exception {

        mockMvc.perform(get(AuthorController.BASE_URL + "/fds"))
                .andExpect(status().isBadRequest());

    }


}