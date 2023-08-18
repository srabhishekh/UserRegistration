package com.login.loginapp.api.srevice;

import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthGetAccessToken;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.login.loginapp.api.model.GoogleOauth;
import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.model.Token;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;

@Service
public class LoginService {

    @Autowired
    IUserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client_id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client_secret}")
    private String clientSecret;

    @Value("${redirect.uri}")
    private String redirectURI;

    public UserDetails login(Authentication auth) throws IOException {
        final String url = "https://oauth2.googleapis.com/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println("token : "+auth.getPrincipal().toString());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","authorization_code");
        map.add("code",auth.getPrincipal().toString());
        map.add("client_id",clientId);
        map.add("client_secret",clientSecret);
        map.add("redirect_uri",redirectURI);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        GoogleOauth response =
                restTemplate.postForObject(url,
                        entity,
                        GoogleOauth.class);
        System.out.println(response);
        System.out.println();
        return null;
    }
}