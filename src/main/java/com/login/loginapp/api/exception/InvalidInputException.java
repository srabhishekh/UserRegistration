package com.login.loginapp.api.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidInputException extends ResponseStatusException {
    public InvalidInputException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
