package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String role);
}