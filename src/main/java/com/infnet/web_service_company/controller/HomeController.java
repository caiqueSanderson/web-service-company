package com.infnet.web_service_company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("API TP3 está no ar! Use /api/employees, /api/products, etc.");
    }
}
