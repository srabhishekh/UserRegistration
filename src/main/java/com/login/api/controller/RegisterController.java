package com.login.api.controller;

import com.login.api.model.RegistrationUserDetails;
import com.login.api.srevice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegistrationUserDetails registrationUserDetails) {
        registerService.registerUser(registrationUserDetails);
        return ResponseEntity.ok().body("User registered successfully");
    }
}