package com.login.loginapp.api.controller;

import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.srevice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/user")
    public String registerUser(@RequestBody UserDetails userDetails) {
        registerService.registerUser(userDetails);
        System.out.println(userDetails);

        return "User registered successfully";
    }

}