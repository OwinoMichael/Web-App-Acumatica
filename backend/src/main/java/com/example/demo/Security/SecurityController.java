package com.example.demo.Security;

import com.example.demo.Users.Users;
import com.example.demo.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = JWTUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(new JWTResponse(jwtToken));

        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

    }

    @PostMapping("/createNewUser")
    public ResponseEntity createNewUser(@RequestBody Users request){
        Optional<Users> customUserOptional = usersRepository.findUsersByEmail(request.getEmail());
        if(customUserOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already exists");
        }

        Users user = new Users();
        user.setId(UUID.randomUUID());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        usersRepository.save(user);
        return ResponseEntity.ok("success");

    }
}