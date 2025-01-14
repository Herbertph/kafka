package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
       if (userRepository.existsByUsername(user.getUsername()) ||
               userRepository.existsByEmail(user.getEmail())) {
           throw new RuntimeException("Username is already taken");
       }
         user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
