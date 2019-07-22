package com.roma.librarymanagment.services;

import com.roma.librarymanagment.dtos.AuthorDTO;
import java.util.List;


public interface AuthorService {
    AuthorDTO add(AuthorDTO author);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    AuthorDTO patchAuthor(Long id, AuthorDTO authorDTO);
    List<AuthorDTO> findAll();
    AuthorDTO findAuthorById(Long id);
    void deleteAuthor(Long id);
    AuthorDTO findAuthorByEmail(String email);


}
