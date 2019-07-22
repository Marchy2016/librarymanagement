package com.roma.librarymanagment.dtos;

import com.roma.librarymanagment.model.Book;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class AuthorDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Book> book = new HashSet<>();
}
