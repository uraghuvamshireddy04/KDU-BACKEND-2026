package com.example.quickship.controllers;

import java.util.List;

import com.example.quickship.jwt.JwtService;
import com.example.quickship.models.AppUser;
import com.example.quickship.models.LoginRequest;
import com.example.quickship.models.TokenResponse;
import com.example.quickship.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepo,
                          PasswordEncoder encoder,
                          AuthenticationManager authManager,
                          JwtService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;

        seedIfMissing("Manager", "man123", List.of("ROLE_MANAGER"));
        seedIfMissing("Driver", "dri123", List.of("ROLE_DRIVER"));
    }

    private void seedIfMissing(String username, String rawPassword, List<String> roles) {
        if (!userRepo.exists(username)) {
            userRepo.save(new AppUser(username, encoder.encode(rawPassword), roles));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Verifies user credentials and generate jwt token")
    public TokenResponse login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        log.info("LOGIN SUCCESS user={}", user.getUsername());
        return new TokenResponse(token);
    }
}


