package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Role;
import com.roma.librarymanagment.model.User;
import com.roma.librarymanagment.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User saveUser(String name, String username, String email, String password, Set<Role> userRoles) {
       User user =  new User(name, username, email, password, userRoles);
       return userRepository.save(user);

    }
}
