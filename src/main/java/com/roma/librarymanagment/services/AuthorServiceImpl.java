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
    public Author updateAuthor(Long id, String firstName, String lastName,String email){
        Author  author = findAuthorById(id);
        if(author != null) {
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setEmail(email);
           }
      return authorRepository.save(author);

    }
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id){
        return authorRepository.findById(id).isPresent() ? authorRepository.findById(id).get() : null;
    }
    public void deleteAuthor(Long id) {
        Author author = findAuthorById(id);
        authorRepository.delete(author);
    }
}
