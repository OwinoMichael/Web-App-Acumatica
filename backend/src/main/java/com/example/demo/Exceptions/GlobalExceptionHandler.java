package com.example.demo.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBaseException.class)
    public ResponseEntity<SimpleResponse> handleProductNotFoundExceptions(CustomBaseException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }


}