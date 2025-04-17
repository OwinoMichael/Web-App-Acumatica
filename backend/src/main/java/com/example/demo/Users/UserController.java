package com.example.demo.Users;

import com.example.demo.Users.commandHandlers.SaveUser;
import com.example.demo.Users.queryHandlers.GetAllUsers;
import com.example.demo.Users.queryHandlers.GetUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetAllUsers getAllUsers;
    private final GetUser getUser;
    private final SaveUser saveUser;

    public UserController(GetAllUsers getAllUsers, GetUser getUser, SaveUser saveUser) {
        this.getAllUsers = getAllUsers;
        this.getUser = getUser;
        this.saveUser = saveUser;
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers(){
        return getAllUsers.execute(null);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Users>> getUser(@RequestParam UUID id){
        return getUser.execute(id);
    }

    @PostMapping("/save")
    ResponseEntity<String> saveUser(@Valid @RequestBody Users users){
        return saveUser.execute(users);
    }
}
