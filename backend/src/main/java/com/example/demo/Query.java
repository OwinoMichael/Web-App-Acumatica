package com.example.demo;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Query <I, O>{
    ResponseEntity<O> execute(I input);
}
