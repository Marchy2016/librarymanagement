package com.roma.librarymanagment.mapper;

import com.roma.librarymanagment.dtos.AuthorDTO;
import com.roma.librarymanagment.model.Author;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    private Mapper mapper;
    public Author authorDTOToAuthor(AuthorDTO authorDTO){

        Author author = new Author();
        mapper.map(authorDTO, author);

        return author;
    }

    public AuthorDTO authorToAuthorDTO(Author author){

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setBook(author.getBook());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());

      //  mapper.map(authorDTO,author);

        return authorDTO;
    }

}
