package com.login.loginapp.api.srevice;

import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.repository.IUserRepository;
import com.login.loginapp.encryption.Argon2HashingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LoginService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    Argon2HashingEngine argonHashingEngine;

    public String login(LoginDetails loginDetails) {

        User user = userRepository.findByUserName(loginDetails.getUserName());

        if (user==null)  return "User not registered";

        argonHashingEngine.setSalt(user.getUserCredentials().getUserSalt());
        argonHashingEngine.encode(Arrays.toString(loginDetails.getPassword()));
        if (argonHashingEngine.getPassword().equalsIgnoreCase(user.getUserCredentials().getUserPassword())) return "Login is success";

        return "Login failed";
    }
}