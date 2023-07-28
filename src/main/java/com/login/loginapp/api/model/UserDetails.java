package com.login.loginapp.api.model;

import java.util.Arrays;

public class UserDetails {
    private String userName;
    private char[] password;
    private String email;
    private String name;

    public UserDetails(String userName, char[] password, String email, String name) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                "userName='" + userName + '\'' +
                ", password=" + Arrays.toString(password) +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
