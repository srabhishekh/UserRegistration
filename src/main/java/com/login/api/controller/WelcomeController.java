package com.login.api.controller;

import com.login.api.srevice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<String> loginUsingCredentials(Authentication authentication) {
        System.out.println("inside welcome controller");
        System.out.println(authentication.getPrincipal());
        return ResponseEntity.ok("Welcome");
    }
}