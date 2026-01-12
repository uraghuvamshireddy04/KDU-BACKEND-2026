package com.example.quickship.controllers;

import java.net.URI;
import java.util.List;

import com.example.quickship.models.PacakgeCreateRequest;
import com.example.quickship.service.PackageService;
import org.springframework.http.ResponseEntity;
import com.example.quickship.models.deliverypackage;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final PackageService service;

    public PackageController(PackageService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER','DRIVER')")
    @Operation(summary = "Returns all packages")
    public List<deliverypackage> list() {
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Starts the async processing")
    public ResponseEntity<deliverypackage> create(@Valid @RequestBody PacakgeCreateRequest req) {
        deliverypackage created = service.addPackageAsync(req.getDestination(), req.getWeight(), req.getDeliveryType());

        return ResponseEntity.accepted()
                .location(URI.create("/packages"))
                .body(created);
    }
}



