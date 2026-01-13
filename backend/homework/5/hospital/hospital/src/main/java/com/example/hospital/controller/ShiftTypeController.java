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
import com.example.hospital.dto.ShiftTypeCreateRequest;
import com.example.hospital.repository.ShiftTypeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tenants/{tenantId}")
public class ShiftTypeController {

  private static final Logger log = LoggerFactory.getLogger(ShiftTypeController.class);
  private final ShiftTypeRepository shiftTypeRepository;

  public ShiftTypeController(ShiftTypeRepository shiftTypeRepository) {
    this.shiftTypeRepository = shiftTypeRepository;
  }

  // Task 1: Native Insert - Shift Types
  @PostMapping("/shift-types")
  public ResponseEntity<CreateResponse> createShiftType(
      @PathVariable UUID tenantId,
      @RequestBody @Valid ShiftTypeCreateRequest req
  ) {
    log.info("Creating shift type name={} tenantId={}", req.name(), tenantId);
    UUID id = shiftTypeRepository.insertShiftType(tenantId, req);
    log.info("Created shift type id={} tenantId={}", id, tenantId);
    return ResponseEntity.ok(new CreateResponse(id));
  }
}
