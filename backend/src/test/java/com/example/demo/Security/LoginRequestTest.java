package com.example.demo.Security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestTest {

    @Test
    public void testGettersAndSetters() {
        LoginRequest request = new LoginRequest();
        request.setEmail("user@example.com");
        request.setPassword("password123");

        assertEquals("user@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    public void testConstructor() {
        LoginRequest request = new LoginRequest("user@example.com", "pass");
        assertEquals("user@example.com", request.getEmail());
        assertEquals("pass", request.getPassword());
    }
}
