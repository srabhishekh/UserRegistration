package com.login.configuration;

import com.login.api.filters.CustomOAuthAuthenticationFilter;
import com.login.api.provider.CustomAuthenticationProvider;
import com.login.api.handler.CustomAuthenticationSuccessHandler;
import com.login.api.handler.CustomAuthenticatorFailureHandler;
import com.login.api.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

//    @Bean
//    public OAuth2LoginAuthenticationFilter oAuth2LoginAuthenticationFilter() throws Exception {
//        CustomOAuthAuthenticationFilter customOAuthAuthenticationFilter = new CustomOAuthAuthenticationFilter(clientRegistrationRepository, oAuth2AuthorizedClientService);
//        customOAuthAuthenticationFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
//        return customOAuthAuthenticationFilter;
//    }

}
