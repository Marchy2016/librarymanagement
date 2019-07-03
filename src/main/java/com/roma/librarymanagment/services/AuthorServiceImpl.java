package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.apache.commons.lang3.StringUtils;
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
       if((!StringUtils.isBlank(firstName)) && (!StringUtils.isBlank(lastName))
               && (!StringUtils.isBlank(email))) {
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
            if ((!StringUtils.isBlank(firstName)) && (!StringUtils.isBlank(lastName))
                    && (!StringUtils.isBlank(email))) {
                author.setFirstName(firstName);
                author.setLastName(lastName);
                author.setEmail(email);
                authorRepository.save(author);
            }
        }
      return author;
    }
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id){
        if(id != null) {
            author = authorRepository.findById(id).isPresent() ? authorRepository.findById(id).get() : null;
        }
        return author;
    }
    public Author findAuthorByEmail(String email){
        if(!StringUtils.isBlank(email)){
           author = authorRepository.findAuthorByEmail(email);
        }
        return author;
    }
    public void deleteAuthor(Long id) {
        if(id != null) {
            authorRepository.delete(findAuthorById(id));
        }
    }
}
