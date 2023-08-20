package com.login.api.controller;

import com.login.api.model.UserDetails;
import com.login.api.srevice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    private UserDetails userDetails = new UserDetails();

    @GetMapping
    public ResponseEntity<UserDetails> loginUsingDetails(Authentication authentication) {
        System.out.println("inside welcome controller");

        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            userDetails.setName(oAuth2User.getAttribute("given_name"));
            return ResponseEntity.ok(userDetails);
        }

        userDetails.setName(authentication.getName());

        System.out.println(authentication.getPrincipal());
        return ResponseEntity.ok(userDetails);
    }
}