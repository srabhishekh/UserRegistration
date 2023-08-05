package com.login.loginapp.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.loginapp.api.config.AuthenticationProvider;
import com.login.loginapp.api.model.LoginDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class LoginFilter extends OncePerRequestFilter {

    private static ObjectMapper mapper = new ObjectMapper();

    private AuthenticationProvider authProvider;

    public LoginFilter(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/login".equalsIgnoreCase(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod())) {
            LoginDetails userDetails = mapper.readValue(request.getInputStream(), LoginDetails.class);
            try {
                SecurityContextHolder.getContext().setAuthentication(authProvider.validateCredentials(userDetails));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request,response);
    }
}