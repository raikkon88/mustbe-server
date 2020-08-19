package com.mustbe.mustbe.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

    private HttpStatus httpStatus;
    private String message;

    public ServiceException(String message, HttpStatus status) {
        this.httpStatus = status;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
