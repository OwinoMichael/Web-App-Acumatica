package com.example.demo.Security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JWTResponseTest {

    @Test
    public void testJWTResponse() {
        JWTResponse response = new JWTResponse("sample.token.value");
        assertEquals("sample.token.value", response.getToken());

        response.setToken("new.token.value");
        assertEquals("new.token.value", response.getToken());
    }
}
