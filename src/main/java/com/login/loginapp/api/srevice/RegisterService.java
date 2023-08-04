package com.login.loginapp.api.srevice;

import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.entity.UserCredentials;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;

    public String registerUser(UserDetails userDetails) {
        passwordEncoder.encode(CharBuffer.wrap(userDetails.getPassword()));

        User user = new User();
        user.setUserName(userDetails.getUserName());
        user.setEmailId(userDetails.getEmail());

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserPassword(passwordEncoder.encode(CharBuffer.wrap(userDetails.getPassword())));
        user.setUserCredentials(userCredentials);
        userCredentials.setUser(user);

        if (userRepository.findByEmailId(userDetails.getEmail())!=null) {
            return "User already registered";
        } else if (userRepository.findByUserName(userDetails.getUserName())!=null) {
            return "e-mail id taken";
        } else {
            userRepository.save(user);
            return "User registration success";
        }
    }
}