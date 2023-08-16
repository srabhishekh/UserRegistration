package com.login.loginapp.api.srevice;

import com.login.loginapp.api.entity.User;
import com.login.loginapp.api.entity.UserCredentials;
import com.login.loginapp.api.exception.InvalidInputException;
import com.login.loginapp.api.model.UserDetails;
import com.login.loginapp.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;

    public void registerUser(UserDetails userDetails) {
        if (userRepository.findByUserName(userDetails.getUsername())!=null) {
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "Username taken");
        } else if (userRepository.findByEmailId(userDetails.getEmail())!=null) {
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "Email address taken");
        } else {
            passwordEncoder.encode(CharBuffer.wrap(userDetails.getPassword()));

            User user = new User();
            user.setUserName(userDetails.getUsername());
            user.setEmailId(userDetails.getEmail());

            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUserPassword(passwordEncoder.encode(CharBuffer.wrap(userDetails.getPassword())));
            user.setUserCredentials(userCredentials);
            userCredentials.setUser(user);

            userRepository.save(user);
        }
    }
}