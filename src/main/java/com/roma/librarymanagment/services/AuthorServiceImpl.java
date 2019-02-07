package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    private Author author;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author add(String firstName, String lastName) {
       // Author author = new Author(firstName, lastName);
        //return authorRepository.save(author);
        if((!"".equals(firstName) && firstName != null) && (!"".equals(lastName) && lastName != null)) {
            author = new Author(firstName, lastName);
        }
        return authorRepository.save(author);
    }
}
