package org.harsh.arya.fooddelivery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/api")
public class HealthController {
    @RequestMapping("/health")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
