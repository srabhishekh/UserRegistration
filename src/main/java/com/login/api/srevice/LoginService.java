package com.login.api.srevice;

import com.login.api.authentication.CustomOauthAuthenticationToken;
import com.login.api.repository.IUserRepository;
import com.login.api.model.GoogleOauth;
import com.login.api.model.UserDetails;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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

    public Authentication login(CustomOauthAuthenticationToken auth) throws IOException {
        final String url = "https://oauth2.googleapis.com/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println("token : "+auth.getToken());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","authorization_code");
        map.add("code",auth.getToken());
        map.add("client_id",clientId);
        map.add("client_secret",clientSecret);
        map.add("redirect_uri",redirectURI);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url,
                        entity,
                        String.class);
        System.out.println(response);
        String responseBody = response.getBody();
        System.out.println(responseBody);
        Gson gson = new Gson();
        GoogleOauth principal = gson.fromJson(responseBody, GoogleOauth.class);
        auth.setPrincipal(principal);
        auth.setAuthenticated(true);
        return auth;
    }
}