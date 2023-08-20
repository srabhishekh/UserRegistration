package com.login.api.advice;

public class ErrorResponse {
    private int errorCode;
    private String errorResponse;

    public ErrorResponse(int errorCode, String errorResponse) {
        this.errorCode = errorCode;
        this.errorResponse = errorResponse;
    }

    public int getErrorCode() {
        return errorCode;
    }

    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    private void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }
}