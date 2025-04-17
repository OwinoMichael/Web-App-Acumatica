package com.example.demo.Users;

import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import com.example.demo.Users.commandHandlers.SaveUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SaveUserTest {

    @Test
    void testExecute() {
        UsersRepository mockRepo = mock(UsersRepository.class);
        SaveUser registerUser = new SaveUser(mockRepo);
        Users user = new Users();

        ResponseEntity<String> response = registerUser.execute(user);

        verify(mockRepo, times(1)).save(user);
        assertEquals("Saved Successfully", response.getBody());
        assertNotNull(user.getId());
    }
}