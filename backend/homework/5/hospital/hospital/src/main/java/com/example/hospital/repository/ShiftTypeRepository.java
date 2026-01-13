package com.example.hospital.repository;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.hospital.dto.ShiftTypeCreateRequest;

@Repository
public class ShiftTypeRepository {

  private final JdbcTemplate jdbcTemplate;

  public ShiftTypeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public UUID insertShiftType(UUID tenantId, ShiftTypeCreateRequest req) {
    String sql = """
      INSERT INTO shift_type (name, description, active, tenant_id)
      VALUES (?, ?, ?, ?)
      RETURNING id
    """;
    return jdbcTemplate.queryForObject(sql, UUID.class,
        req.name(), req.description(), req.active(), tenantId);
  }
}
