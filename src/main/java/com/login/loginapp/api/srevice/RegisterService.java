package com.login.loginapp.api.srevice;

import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.entity.UserCredentials;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;

    public ResponseEntity registerUser(UserDetails userDetails) {
        if (userRepository.findByEmailId(userDetails.getEmail())!=null) {
            return ResponseEntity.badRequest().body("e-Mail id taken");
        } else if (userRepository.findByUserName(userDetails.getUserName())!=null) {
            return ResponseEntity.badRequest().body("User name taken");
        } else {
            passwordEncoder.encode(CharBuffer.wrap(userDetails.getPassword()));

            User user = new User();
            user.setUserName(userDetails.getUserName());
            user.setEmailId(userDetails.getEmail());

            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUserPassword(passwordEncoder.encode(CharBuffer.wrap(userDetails.getPassword())));
            user.setUserCredentials(userCredentials);
            userCredentials.setUser(user);

            userRepository.save(user);
            return ResponseEntity.ok("User registration success");
        }
    }
}