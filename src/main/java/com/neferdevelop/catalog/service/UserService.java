package com.neferdevelop.catalog.service;

import com.neferdevelop.catalog.entity.User;
import com.neferdevelop.catalog.form.UserRepresentation;
import com.neferdevelop.catalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void create(UserRepresentation userRepresentation){
        User user = new User();
        user.setUsername(userRepresentation.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRepresentation.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
