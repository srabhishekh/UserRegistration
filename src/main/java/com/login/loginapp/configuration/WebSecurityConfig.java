package com.login.loginapp.configuration;

import com.login.loginapp.api.config.AuthenticationProvider;
import com.login.loginapp.api.filters.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    AuthenticationProvider authProvider;

    public WebSecurityConfig(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
            http.csrf(csfr -> csfr.disable());
            http.addFilterBefore(new LoginFilter(authProvider), BasicAuthenticationFilter.class);
            http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/register", "/login").
                    permitAll().
                    anyRequest().authenticated());
            return http.build();
    }
}