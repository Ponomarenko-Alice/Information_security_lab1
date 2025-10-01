package com.ITMO.informationSecurity.controller;

import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.repository.DataRepository;
import com.ITMO.informationSecurity.repository.UserRepository;
import com.ITMO.informationSecurity.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {
    private final JwtService jwtService;
    private final DataRepository dataRepository;
    private final UserRepository userRepository;

    private static String extractBearer(String header) {
        if (header == null) return null;
        if (!header.startsWith("Bearer ")) return null;
        return header.substring(7);
    }

    @GetMapping("/poem")
    public ResponseEntity<List<String>> getPoem(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

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


        return ResponseEntity.ok(dataRepository.getPoem());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

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


        return ResponseEntity.ok(userRepository.findAll());
    }

}
