package com.login.loginapp.configuration;

import com.login.loginapp.encryption.Argon2HashingEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Argon2HashingEngine getArgon2Hashing() {
        return new Argon2HashingEngine();
    }
}
