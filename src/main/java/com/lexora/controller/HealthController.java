package com.lexora.controller;

import com.lexora.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Custom health and status controller for Lexora.
 * Spring Boot Actuator health is also available at /actuator/health.
 */
@RestController
@RequestMapping("/v1")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, String>>> health() {
        Map<String, String> status = Map.of(
                "status", "UP",
                "service", "Lexora API",
                "version", "1.0.0-SNAPSHOT"
        );
        return ResponseEntity.ok(ApiResponse.success("Lexora is running", status));
    }
}
