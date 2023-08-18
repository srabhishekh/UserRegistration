package com.login.loginapp.api.model;

public class Token {
    private String code;

    public Token(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String token) {
        this.code = token;
    }
}
