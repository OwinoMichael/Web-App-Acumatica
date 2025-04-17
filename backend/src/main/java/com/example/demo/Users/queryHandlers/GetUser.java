package com.example.demo.Users.queryHandlers;

import com.example.demo.Query;
import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUser implements Query<UUID, Optional<Users>> {

    private final UsersRepository usersRepository;

    public GetUser(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<Optional<Users>> execute(UUID id) {
        Optional<Users> users = usersRepository.findById(id);

        return ResponseEntity.ok(users);

    }
}
