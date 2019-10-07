package com.roma.librarymanagment.services;

import com.roma.librarymanagment.Exceptions.NotFoundException;
import com.roma.librarymanagment.dtos.AuthorDTO;

import com.roma.librarymanagment.mapper.AuthorMapper1;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.repositories.AuthorRepository;
import org.apache.commons.lang3.ObjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private static Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private AuthorMapper1 authorMapper;

    public AuthorServiceImpl(AuthorMapper1 authorMapper, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO add(AuthorDTO author) {

        Author author1 = authorMapper.authorDTOToAuthor(author);
        return authorMapper.authorToAuthorDTO(authorRepository.save(author1));
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {

        return authorRepository.findById(id).map(author1 -> {
            if (authorDTO.getFirstName() != null) {
                author1.setFirstName(authorDTO.getFirstName());
            }
            if (authorDTO.getLastName() != null) {
                author1.setLastName(authorDTO.getLastName());
            }
            if (authorDTO.getEmail() != null) {
                author1.setEmail(authorDTO.getEmail());
            }
            return authorMapper.authorToAuthorDTO(authorRepository.save(author1));

        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public AuthorDTO patchAuthor(Long id, AuthorDTO authorDTO) {

        return authorRepository.findById(id).map(author1 -> {
            if (!author1.getFirstName().equalsIgnoreCase(authorDTO.getFirstName())) {
                author1.setFirstName(authorDTO.getFirstName());
            }
            if (!author1.getLastName().equalsIgnoreCase(authorDTO.getLastName())) {
                author1.setLastName(authorDTO.getLastName());
            }
            if (!author1.getEmail().equalsIgnoreCase(authorDTO.getEmail())) {
                author1.setEmail(authorDTO.getEmail());
            }
            return authorMapper.authorToAuthorDTO(authorRepository.save(author1));

        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<AuthorDTO> findAll() {

        return authorRepository.findAll()
                .stream()
                .map(authorMapper::authorToAuthorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findAuthorById(Long id) {

        return authorRepository.findById(id).map(author -> {
            if (ObjectUtils.isEmpty(author)) {
                throw new NotFoundException("Author with id " + id.toString() + " not found");
            }
            return authorMapper.authorToAuthorDTO(author);

        }).orElseThrow(NotFoundException::new);

    }

    @Override
    public AuthorDTO findAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email).map(author -> {
            if (ObjectUtils.isEmpty(author)) {
                throw new NotFoundException("Author with email " + email + " not found");
            }
            return authorMapper.authorToAuthorDTO(author);

        }).orElseThrow(RuntimeException::new);

    }

    @Override
    public void deleteAuthor(Long id) {
        if (id != null) {
            try {
                AuthorDTO author = findAuthorById(id);
                if (ObjectUtils.isNotEmpty(author)) {
                    authorRepository.delete(authorMapper.authorDTOToAuthor(author));
                }
            } catch (NullPointerException ex) {
                logger.error("Author with id {} does not exists", id);
            }
        }
    }
}
