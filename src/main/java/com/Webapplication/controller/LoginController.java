package com.Webapplication.controller;


import com.Webapplication.model.User;
import com.Webapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login() {
        return "Login page..";
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody User user) {
        // Retrieve user by email
        Optional<User> optionalUser = userService.findByEmail(user.getEmail());

        // Check if user exists and password is correct
        if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // Get the user object from Optional
        User foundUser = optionalUser.get();

        // Set login status and save changes
        foundUser.setLoggedIn(true);  // Set the 'isLoggedIn' field to true
        userService.registerUser(foundUser);  // Persist the login status

        // Return the full user details upon successful sign-in
        return ResponseEntity.ok("Login successful");
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }



}
