package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private Author author;

    private static Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author add(String firstName, String lastName, String email) {
        if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)
                && StringUtils.isNotBlank(email)) {
            author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setEmail(email);
        }
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, String firstName, String lastName, String email) {
        author = findAuthorById(id);
        if (ObjectUtils.isNotEmpty(author)) {
            if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)
                    && StringUtils.isNotBlank(email)) {
                author.setFirstName(firstName);
                author.setLastName(lastName);
                author.setEmail(email);
                authorRepository.save(author);
            }
        }
        return author;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id) {
        if (id != null) {
            try {
                author = authorRepository.findById(id).isPresent() ? authorRepository.findById(id).get() : null;
            } catch (NullPointerException ex) {
                logger.error("Author with id {} does not exists", id);
            }
        }
        return author;
    }

    public Author findAuthorByEmail(String email) {
        if (StringUtils.isNotEmpty(email)) {
            try {
                author = authorRepository.findAuthorByEmail(email).isPresent() ? authorRepository.findAuthorByEmail(email).get() : null;
            } catch (NullPointerException ex) {
                logger.error("Author with email {} does not exists", email);
            }

        }
        return author;
    }
    public void deleteAuthor(Long id) {
        if (id != null) {
            try {
                author = findAuthorById(id);
                if (ObjectUtils.isNotEmpty(author)) {
                    authorRepository.delete(author);
                }
            } catch (NullPointerException ex) {
                logger.error("Author with id {} does not exists", id);
            }
        }
    }
}
