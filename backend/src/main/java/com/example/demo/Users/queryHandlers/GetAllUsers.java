package com.example.demo.Users.queryHandlers;

import com.example.demo.Query;
import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsers implements Query<Void, List<Users>> {


    private final UsersRepository usersRepository;

    public GetAllUsers(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<List<Users>> execute(Void input) {
        List<Users> users = usersRepository.findAll();

        return ResponseEntity.ok(users);
    }
}
