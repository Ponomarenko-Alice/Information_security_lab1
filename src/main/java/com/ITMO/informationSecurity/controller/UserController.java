package com.ITMO.informationSecurity.controller;

import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.service.JwtService;
import com.ITMO.informationSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final JwtService jwtService;

    private static String extractBearer(String header) {
        if (header == null) return null;
        if (!header.startsWith("Bearer ")) return null;
        return header.substring(7);
    }

    @GetMapping("/{username}")
    public User byUsername(@PathVariable String username, @RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkAuth(authHeader);
        return service.getByUsername(username);
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam(defaultValue = "") String q, @RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkAuth(authHeader);
        return service.search(q);
    }

    private void checkAuth(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        String token = extractBearer(authHeader);
        if (token == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Bearer token");

        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        if (!jwtService.isTokenValid(token, username)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }
}
