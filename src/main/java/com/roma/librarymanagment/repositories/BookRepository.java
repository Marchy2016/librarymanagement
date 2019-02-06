package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Optional<Book> findById(Long id);


}
