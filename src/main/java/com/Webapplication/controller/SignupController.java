package com.Webapplication.controller;

import com.Webapplication.model.User;
import com.Webapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/auth")
public class SignupController {
        @Autowired
        private UserService userService;
    public String signup() {
        return "Sign Up page..";
    }

        // Signup API
        @PostMapping("/signup")
        public ResponseEntity<String> signup(@RequestBody User user) {
            if (userService.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            if (user.getFirstname() == null || user.getLastname() == null || user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Required fields are missing");
            }

            user.setCreatedAt(LocalDateTime.now());
            user.setModifiedAt(LocalDateTime.now());
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        }
    }


