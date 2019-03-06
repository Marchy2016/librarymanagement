package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Role;
import com.roma.librarymanagment.model.User;

import java.util.Set;

public interface UserService {

    User findUserByEmail(String email);
    User saveUser(String name, String username, String email, String password, Set<Role> userRoles);
}
