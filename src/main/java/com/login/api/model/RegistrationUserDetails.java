package com.login.api.model;

import java.util.Arrays;

public class RegistrationUserDetails {
    private String username;
    private char[] password;
    private String email;
    private String name;

    public RegistrationUserDetails(String username, char[] password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userName='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
