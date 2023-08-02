package com.login.loginapp.api.entity;

import jakarta.persistence.*;

@Entity
public class UserCredentials {
    @Id
    private Long userId;
    private String userPassword;
    private byte[] userSalt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserCredentials() {
    }

    public UserCredentials(Long userId, String userPassword, byte[] userSalt) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userSalt = userSalt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public byte[] getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(byte[] userSalt) {
        this.userSalt = userSalt;
    }
}
