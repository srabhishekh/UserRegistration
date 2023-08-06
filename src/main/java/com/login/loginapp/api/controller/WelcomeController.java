package com.login.loginapp.api.controller;

import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.srevice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<String> loginUsingCredentials(@AuthenticationPrincipal User userDetails) {
        System.out.println("inside login controller");
        System.out.println(userDetails);
        return ResponseEntity.ok("Welcome : "+userDetails.getUsername());
    }
}