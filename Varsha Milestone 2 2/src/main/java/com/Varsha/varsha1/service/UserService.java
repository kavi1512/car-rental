package com.Varsha.varsha1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Varsha.varsha1.model.User;
import com.Varsha.varsha1.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        // Only encode the password if it's not already encoded (assuming passwords are encoded using BCrypt)
        if (!isPasswordEncoded(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    // Helper method to check if the password is already encoded
    private boolean isPasswordEncoded(String password) {
        // BCrypt passwords have 60 characters starting with $2a$ or $2b$, etc.
        return password.startsWith("$2a$") || password.startsWith("$2b$");
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);  // Assuming you're using a JPA repository
    }
    
}

