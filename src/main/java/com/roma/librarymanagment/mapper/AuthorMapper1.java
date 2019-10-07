package com.roma.librarymanagment.mapper;

import com.roma.librarymanagment.dtos.AuthorDTO;
import com.roma.librarymanagment.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper1 {

    AuthorMapper1 INSTANCE = Mappers.getMapper(AuthorMapper1.class);

    AuthorDTO authorToAuthorDTO(Author author);
    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
