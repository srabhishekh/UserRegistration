package com.login.loginapp.api.controller;

import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.srevice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public String loginUsingCredentials(@RequestBody LoginDetails loginDetails) {
        return loginService.login(loginDetails);
    }
}
