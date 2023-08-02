package com.login.loginapp.api.srevice;

import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.entity.UserCredentials;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.repository.IUserRepository;
import com.login.loginapp.encryption.Argon2HashingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegisterService {

    @Autowired
    private Argon2HashingEngine engine;
    @Autowired
    private IUserRepository userRepository;

    public String registerUser(UserDetails userDetails) {
        engine.encode(Arrays.toString(userDetails.getPassword()));

        User user = new User();
        user.setUserName(userDetails.getUserName());
        user.setEmailId(userDetails.getEmail());

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserPassword(engine.getPassword());
        userCredentials.setUserSalt(engine.getSalt());
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