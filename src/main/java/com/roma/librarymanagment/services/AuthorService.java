package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;

import java.util.List;

public interface AuthorService {
    Author add(String firstName, String lastName);
    List<Author> findAll();

}
