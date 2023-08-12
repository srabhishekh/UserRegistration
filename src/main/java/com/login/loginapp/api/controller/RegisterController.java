package com.login.loginapp.api.controller;

import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.srevice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserDetails userDetails) {
        registerService.registerUser(userDetails);
        return ResponseEntity.ok().body("User registered successfully");
    }
}