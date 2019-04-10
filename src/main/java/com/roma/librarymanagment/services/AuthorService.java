package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;

import java.util.List;


public interface AuthorService {
    Author add(String name, String surname,String email);
    Author updateAuthor(Long id, String firstName, String lastName,String email);
    List<Author> findAll();
    Author findAuthorById(Long id);
    void deleteAuthor(Long id);


}
