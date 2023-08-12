package com.login.loginapp.configuration;

import com.login.loginapp.api.handler.CustomAuthenticationSuccessHandler;
import com.login.loginapp.api.handler.CustomAuthenticatorFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticatorFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

}
