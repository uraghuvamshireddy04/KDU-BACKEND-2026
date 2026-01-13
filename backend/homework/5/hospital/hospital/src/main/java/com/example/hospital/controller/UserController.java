package com.example.hospital.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.dto.CreateResponse;
import com.example.hospital.dto.UserCreateRequest;
import com.example.hospital.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tenants/{tenantId}")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Task 1: Native Insert - Users
  @PostMapping("/users")
  public ResponseEntity<CreateResponse> createUser(
      @PathVariable UUID tenantId,
      @RequestBody @Valid UserCreateRequest req
  ) {
    log.info("Creating user username={} tenantId={}", req.username(), tenantId);
    UUID id = userRepository.insertUser(tenantId, req);
    log.info("Created user id={} tenantId={}", id, tenantId);
    return ResponseEntity.ok(new CreateResponse(id));
  }
}
