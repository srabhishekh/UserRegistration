package com.login.loginapp.api.config;

import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.srevice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider {

    @Autowired
    LoginService loginService;

    public Authentication validateCredentials(LoginDetails userDetails) {
       return new UsernamePasswordAuthenticationToken(loginService.login(userDetails), null);
    }
}