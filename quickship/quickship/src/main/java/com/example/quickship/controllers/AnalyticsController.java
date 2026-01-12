package com.example.quickship.controllers;
import java.util.Map;

import com.example.quickship.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final PackageService packageService;

    public AnalyticsController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/totalRevenue")
    @PreAuthorize("hasAnyRole('MANAGER','DRIVER')")
    @Operation(summary = "Returns total revenue from all packages")
    public double audit() {
        return packageService.totalRevenue();
    }
}


