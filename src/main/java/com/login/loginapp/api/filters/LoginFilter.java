package com.login.loginapp.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.model.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        if("/login-with-google".equalsIgnoreCase(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod())) {
            Token token = mapper.readValue(request.getInputStream(), com.login.loginapp.api.model.Token.class);
            System.out.println(token.getCode());
            String tokenStr = token.getCode();
            System.out.println(tokenStr);
            Authentication auth = new UsernamePasswordAuthenticationToken(tokenStr, "");
            try {
                SecurityContextHolder.getContext().setAuthentication(authProvider.authenticate(auth));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request,response);
    }
}