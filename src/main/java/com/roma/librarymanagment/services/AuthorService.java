package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;

import java.util.List;


public interface AuthorService {
    Author add(String name, String surname,String email);
    List<Author> findAll();
    Author findById(Long id);


}
