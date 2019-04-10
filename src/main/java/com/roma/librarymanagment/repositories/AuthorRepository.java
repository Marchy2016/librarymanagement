package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("update Author set firstName = firstName, lastName = lastName, email = email where id = id")
    Author updateAuthor(Long id, String firstName, String lastName,String email);
    Author findAuthorByEmail(String email);

}
