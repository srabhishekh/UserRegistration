package com.login.loginapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig {

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CorsConf corsConfigurationSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 66536, 2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/register").
                permitAll());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        http.csrf(csfr -> csfr.disable());
        http.formLogin(Customizer.withDefaults());
        http.formLogin(login -> login.successHandler(authenticationSuccessHandler));
        http.formLogin(login -> login.failureHandler(authenticationFailureHandler));
        http.logout(Customizer.withDefaults());
        http.logout(logout -> logout.invalidateHttpSession(true));
        http.logout(logout -> logout.deleteCookies("JSESSIONID"));
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource));
        return http.build();
    }
}