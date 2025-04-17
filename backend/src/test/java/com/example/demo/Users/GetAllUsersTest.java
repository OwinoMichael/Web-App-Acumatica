package com.example.demo.Users;

import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import com.example.demo.Users.queryHandlers.GetAllUsers;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetAllUsersTest {

    @Test
    void testExecute() {
        UsersRepository mockRepo = mock(UsersRepository.class);
        List<Users> mockList = Arrays.asList(new Users(), new Users());
        when(mockRepo.findAll()).thenReturn(mockList);

        GetAllUsers getAllUsers = new GetAllUsers(mockRepo);
        ResponseEntity<List<Users>> response = getAllUsers.execute(null);

        assertEquals(2, response.getBody().size());
    }
}