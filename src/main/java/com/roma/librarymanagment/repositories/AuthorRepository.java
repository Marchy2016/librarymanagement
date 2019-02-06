package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
