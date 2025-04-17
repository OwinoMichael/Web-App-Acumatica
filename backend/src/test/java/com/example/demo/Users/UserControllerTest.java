package com.example.demo.Users;

import com.example.demo.Users.commandHandlers.SaveUser;
import com.example.demo.Users.queryHandlers.GetAllUsers;
import com.example.demo.Users.queryHandlers.GetUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private GetAllUsers getAllUsers;

    @Mock
    private GetUser getUser;

    @Mock
    private SaveUser registerUser;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<Users> mockList = Arrays.asList(new Users(), new Users());
        when(getAllUsers.execute(null)).thenReturn(ResponseEntity.ok(mockList));

        ResponseEntity<List<Users>> response = userController.getAllUsers();

        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetUser() {
        UUID id = UUID.randomUUID();
        Optional<Users> user = Optional.of(new Users());
        when(getUser.execute(id)).thenReturn(ResponseEntity.ok(user));

        ResponseEntity<Optional<Users>> response = userController.getUser(id);

        assertTrue(response.getBody().isPresent());
    }

    @Test
    void testSaveUser() {
        Users user = new Users();
        when(registerUser.execute(user)).thenReturn(ResponseEntity.ok("Saved Successfully"));

        ResponseEntity<String> response = userController.saveUser(user);

        assertEquals("Saved Successfully", response.getBody());
    }
}
