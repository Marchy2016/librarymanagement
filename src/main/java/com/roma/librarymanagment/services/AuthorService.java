package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;

import java.util.List;


public interface AuthorService {
    Author add(String name, String surname);
    List<Author> findAll();


}
