package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
