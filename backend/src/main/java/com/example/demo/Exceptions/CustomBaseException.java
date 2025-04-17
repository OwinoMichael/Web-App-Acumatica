package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

public class CustomBaseException extends RuntimeException {

    private final HttpStatus status;
    private final SimpleResponse simpleResponse;

    public CustomBaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.simpleResponse = new SimpleResponse(message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public SimpleResponse getSimpleResponse() {
        return simpleResponse;
    }
}