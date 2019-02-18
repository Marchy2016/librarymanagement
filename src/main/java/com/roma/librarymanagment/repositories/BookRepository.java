package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
}
