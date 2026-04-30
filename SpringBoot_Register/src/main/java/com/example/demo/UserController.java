package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    // Changed to GetMapping so it works directly in your browser
    @GetMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email) {
        return "User Registered Successfully!\nName: " + name + "\nEmail: " + email;
    }
}
