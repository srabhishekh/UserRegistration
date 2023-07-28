package com.login.loginapp.srevice;

import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.encruption.Argon2Hashing;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
public class RegisterService {
    public void registerUser(UserDetails userDetails) {
        BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom(16);
        System.out.println(Arrays.toString(userDetails.getPassword()));
        Argon2Hashing encoder = new Argon2Hashing(Base64.getDecoder().decode("AAAAAAAAAAAAAAAAAAAAAA=="));
        System.out.println(encoder.encode(Arrays.toString(userDetails.getPassword())));
    } 
}