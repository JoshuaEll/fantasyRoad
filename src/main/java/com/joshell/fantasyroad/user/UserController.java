package com.joshell.fantasyroad.user;

import com.joshell.fantasyroad.config.auth.AuthenticationResponse;
import com.joshell.fantasyroad.config.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserRole (@PathVariable String email){
        User user = repository.findUserByEmail(email);
        String role = user.getRole().toString();
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }
}
