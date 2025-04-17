package com.example.demo.Security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JWTUtilTest {

    @Test
    public void testGenerateAndExtractUsername() {
        String username = "testuser@example.com";
        String token = JWTUtil.generateToken(username);

        String extractedUsername = JWTUtil.extractUsername(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    public void testValidateTokenValid() {
        String token = JWTUtil.generateToken("user@example.com");
        assertTrue(JWTUtil.validateToken(token));
    }

    @Test
    public void testValidateTokenInvalid() {
        String invalidToken = "some.invalid.token";
        assertFalse(JWTUtil.validateToken(invalidToken));
    }
}