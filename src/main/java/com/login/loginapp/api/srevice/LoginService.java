package com.login.loginapp.api.srevice;

import com.login.loginapp.api.model.LoginDetails;
import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class LoginService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDetails login(LoginDetails loginDetails) {

        User user = userRepository.findByUserName(loginDetails.getUserName());

        if (user==null)  throw new RuntimeException();

        if (passwordEncoder.matches(CharBuffer.wrap(loginDetails.getPassword()), user.getUserCredentials().getUserPassword())) {
            return new UserDetails(Long.toString(user.getUserId()),null,user.getEmailId(), user.getUserName());
        } else throw new RuntimeException();
    }
}