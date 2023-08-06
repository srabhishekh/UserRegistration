package com.login.loginapp.api.srevice;

import com.login.loginapp.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.login.loginapp.api.entity.User user = userRepository.findByUserName(username);

        UserDetails userDetails = User.withUsername(user.getUserName()).password(user.getUserCredentials().getUserPassword())
                .roles("USER_ROLE")
                .build();
        System.out.println(userDetails);
         return userDetails;
    }
}
