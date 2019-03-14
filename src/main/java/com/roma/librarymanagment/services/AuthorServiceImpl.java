package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    private Author author;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author add(String firstName, String lastName,String email) {
       if((!"".equals(firstName) && firstName != null) && (!"".equals(lastName) && lastName != null)
               && (!"".equals(email) && email != null)) {
            author = new Author(firstName, lastName,email);
        }
        return authorRepository.save(author);
    }
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findById(Long id){
        return authorRepository.findById(id).isPresent() ? authorRepository.findById(id).get() : null;
    }
}
