package com.Varsha.varsha1.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.StompBrokerRelayMessageHandler;
import org.springframework.web.bind.annotation.*;

import com.Varsha.varsha1.controller.EmailSenderService;
import com.Varsha.varsha1.model.User;
import com.Varsha.varsha1.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
public ResponseEntity<?> signin(@RequestBody User user) {
    // Retrieve user by email or return null if not found
    User foundUser = userService.findByEmail(user.getEmail()).orElse(null);

    // Check if user exists and password is correct
    if (foundUser == null || !userService.checkPassword(foundUser, user.getPassword())) {
        return ResponseEntity.badRequest().body("Invalid email or password");
    }

    // Set login status and save changes
    foundUser.setIsLoggedIn(true);
    userService.registerUser(foundUser);  // Persist the login status

    // Return the full user details upon successful sign-in
    return ResponseEntity.ok(foundUser);
}


    @PostMapping("/signout")
    public ResponseEntity<String> signout(@RequestBody User user) {
        User foundUser = userService.findByEmail(user.getEmail())
                .orElse(null);

        if (foundUser == null) {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        foundUser.setIsLoggedIn(false);  // Update isLoggedIn to false
        userService.registerUser(foundUser);  // Persist changes in the database

        return ResponseEntity.ok("User signed out successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody User user) {
        User foundUser = userService.findByEmail(user.getEmail())
                .orElse(null);

        if (foundUser == null) {
            return ResponseEntity.badRequest().body("Email not found");
        }

        foundUser.setModifiedAt(LocalDateTime.now());
        foundUser.setPassword(user.getPassword());
        userService.registerUser(foundUser);

        return ResponseEntity.ok("Password reset successfully");
    }


    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailSenderService.sendSimpleEmail(emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getBody());
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }
    }
    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        System.out.println("this is mail"+email);
        // Step 1: Find the user by email
        User foundUser = userService.findByEmail(email).orElse(null);

        if (foundUser == null) {
            return ResponseEntity.badRequest().body(null);  // User not found, return null or appropriate error
        }

        // Step 2: Return the user data in response
        return ResponseEntity.ok(foundUser);
    }
    
}

class EmailRequest {
    private String toEmail;
    private String subject;
    private String body;

    // Getters and Setters
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}


