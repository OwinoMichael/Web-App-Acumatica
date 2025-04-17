package com.example.demo.Users.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.CustomBaseException;
import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaveUser implements Command<Users, String> {

    private final UsersRepository usersRepository;

    public SaveUser(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<String> execute(Users users) {
        validateUser(users);

        if (users.getId() == null) {
            users.setId(UUID.randomUUID()); // 👈 manually generate UUID
        }

        usersRepository.save(users);
        return ResponseEntity.ok("Saved Successfully");
    }

    private void validateUser(Users user) {
        if (user.getFullName() == null || user.getFullName().isBlank()) {
            throw new CustomBaseException(HttpStatus.BAD_REQUEST, "Full name is required");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new CustomBaseException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new CustomBaseException(HttpStatus.BAD_REQUEST, "Invalid email format");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new CustomBaseException(HttpStatus.BAD_REQUEST, "Password must be at least 6 characters long");
        }

        if (user.getCreatedAt() == null) {
            throw new CustomBaseException(HttpStatus.BAD_REQUEST, "Created date is required");
        }

        if (user.getUpdatedAt() == null) {
            throw new CustomBaseException(HttpStatus.BAD_REQUEST, "Updated date is required");
        }
    }

}
