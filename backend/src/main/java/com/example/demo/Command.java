package com.example.demo;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Command <I, T>{
    ResponseEntity<T> execute(I input);
}
