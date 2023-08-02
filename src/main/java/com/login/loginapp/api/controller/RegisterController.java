package com.login.loginapp.api.controller;

import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.srevice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/user")
    public String registerUser(@RequestBody UserDetails userDetails) {
        return registerService.registerUser(userDetails);
    }
}