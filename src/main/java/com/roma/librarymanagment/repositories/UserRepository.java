package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
