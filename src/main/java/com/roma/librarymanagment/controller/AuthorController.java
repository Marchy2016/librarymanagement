package com.roma.librarymanagment.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.roma.librarymanagment.Exceptions.NotFoundException;
import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.dtos.AuthorDTO;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.services.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(AuthorController.BASE_URL)
public class AuthorController {

    protected static final String BASE_URL = "/api/v1/authors";
    private AuthorService authorService;


    private static Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private WebDataBinder webDataBinder;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    @GetMapping
    @ApiOperation("List all authors")
    public List<AuthorDTO> listAuthors() {
        return authorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a new author")
    private AuthorDTO saveAuthor(@Valid @RequestBody AuthorDTO author) {
        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                logger.error(objectError.toString());
            });
            return null;
        }
        return  authorService.add(author);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update author")
    private ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authr) {

       return new ResponseEntity<>(authorService.updateAuthor(id, authr),HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Patch author")
    private ResponseEntity<AuthorDTO> patchAuthor(@PathVariable Long id, @RequestBody AuthorDTO authr) {

        return new ResponseEntity<>(authorService.patchAuthor(id, authr),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get author by id")
    private AuthorDTO findAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete author")
    private void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private void notFoundExcetion(Exception ex) {
        logger.error("Exception : Author not found");
    }

}
