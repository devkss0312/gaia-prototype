package com.example.gaia.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> getClientIp(HttpServletRequest request) {
        // 클라이언트의 IP 주소 가져오기
        String clientIp = request.getRemoteAddr();
        return ResponseEntity.ok(clientIp);
    }
}
