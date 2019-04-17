package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private Author author;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author add(String firstName, String lastName,String email){
       if((!"".equals(firstName) && firstName != null) && (!"".equals(lastName) && lastName != null)
               && (!"".equals(email) && email != null)) {
               author = new Author();
               author.setFirstName(firstName);
               author.setLastName(lastName);
               author.setEmail(email);
           }

        return authorRepository.save(author);
    }
    public Author updateAuthor(Long id, String firstName, String lastName,String email){
        author = findAuthorById(id);
        if(author != null) {
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setEmail(email);
            authorRepository.save(author);
           }
      return author;
    }
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id){
        return authorRepository.findById(id).isPresent() ? authorRepository.findById(id).get() : null;
    }
    public Author findAuthorByEmail(String email){
        return authorRepository.findAuthorByEmail(email);
    }
    public void deleteAuthor(Long id) {
        author = findAuthorById(id);
        authorRepository.delete(author);
    }
}
