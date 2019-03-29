package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
