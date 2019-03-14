package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
