package com.example.demo.Users;

import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import com.example.demo.Users.queryHandlers.GetUser;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetUserTest {

    @Test
    void testExecute() {
        UsersRepository mockRepo = mock(UsersRepository.class);
        UUID id = UUID.randomUUID();
        Optional<Users> mockUser = Optional.of(new Users());
        when(mockRepo.findById(id)).thenReturn(mockUser);

        GetUser getUser = new GetUser(mockRepo);
        ResponseEntity<Optional<Users>> response = getUser.execute(id);

        assertTrue(response.getBody().isPresent());
    }
}
