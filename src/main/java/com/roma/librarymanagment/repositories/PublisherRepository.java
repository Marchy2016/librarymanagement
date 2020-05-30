package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("SELECT p FROM Publisher p WHERE p.email = email")
    Publisher findByEmail(String email);
}
