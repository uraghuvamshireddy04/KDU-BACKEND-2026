package com.example.hospital.repository;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.hospital.dto.UserCreateRequest;

@Repository
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public UUID insertUser(UUID tenantId, UserCreateRequest req) {
    String sql = """
      INSERT INTO users (username, logged_in, time_zone, tenant_id)
      VALUES (?, ?, ?, ?)
      RETURNING id
    """;
    return jdbcTemplate.queryForObject(sql, UUID.class,
        req.username(), req.loggedIn(), req.timeZone(), tenantId);
  }
}
