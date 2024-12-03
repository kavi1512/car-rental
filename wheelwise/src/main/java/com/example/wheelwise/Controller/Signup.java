package com.example.wheelwise.Controller;

import java.time.LocalDateTime;

import com.example.wheelwise.Model.User;
import com.example.wheelwise.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Signup {

    @Autowired
    UserService userService;
    @RequestMapping("/signup")
    public String signup() {
        return "Sign Up page..";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }


        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
    @RequestMapping("/")
    public String greet(){
        return "This is wheelwise app";
    }
}
