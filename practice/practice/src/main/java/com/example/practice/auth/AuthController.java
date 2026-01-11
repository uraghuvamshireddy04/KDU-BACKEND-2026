package com.example.practice.auth;

import java.util.List;

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

import com.example.practice.jwt.JwtService;
import com.example.practice.user.AppUser;
import com.example.practice.user.UserRepository;

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

        seedIfMissing("librarian", "lib123", List.of("ROLE_LIBRARIAN"));
        seedIfMissing("member", "mem123", List.of("ROLE_MEMBER"));
    }

    private void seedIfMissing(String username, String rawPassword, List<String> roles) {
        if (!userRepo.exists(username)) {
            userRepo.save(new AppUser(username, encoder.encode(rawPassword), roles));
        }
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest req) {
        if (userRepo.exists(req.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Convert "MEMBER" -> "ROLE_MEMBER"
        List<String> roles = req.getRoles().stream()
                .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                .toList();

        userRepo.save(new AppUser(req.getUsername(), encoder.encode(req.getPassword()), roles));

        log.info("REGISTER SUCCESS user={} roles={}", req.getUsername(), roles);
        return "Registered: " + req.getUsername();
    }

    @PostMapping("/login")
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

