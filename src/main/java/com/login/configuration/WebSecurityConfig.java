package com.login.configuration;

import com.login.api.filters.CustomOAuthAuthenticationFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    CorsConf corsConfigurationSource;

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 66536, 2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/register",
                "/login-with-google").
                permitAll());
//        http.addFilterBefore(
//                new CustomOAuthAuthenticationFilter(clientRegistrationRepository, oAuth2AuthorizedClientService), BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        http.csrf(csfr -> csfr.disable());
        http.formLogin(Customizer.withDefaults());
        http.oauth2Login(login -> login.successHandler(authenticationSuccessHandler));
        http.formLogin(login -> login.successHandler(authenticationSuccessHandler));
        http.formLogin(login -> login.failureHandler(authenticationFailureHandler));
        http.logout(Customizer.withDefaults());
        http.logout(logout -> logout.invalidateHttpSession(true));
        http.logout(logout -> logout.deleteCookies("JSESSIONID"));
        http.logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler));
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource));
        //http.addFilter(getCustomFilter());
        return http.build();
    }

    private Filter getCustomFilter() throws Exception {
        CustomOAuthAuthenticationFilter customOAuthAuthenticationFilter = new CustomOAuthAuthenticationFilter(clientRegistrationRepository, oAuth2AuthorizedClientService);
        customOAuthAuthenticationFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        return customOAuthAuthenticationFilter;
    }
}