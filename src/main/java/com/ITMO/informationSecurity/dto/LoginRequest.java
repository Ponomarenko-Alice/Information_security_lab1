package com.ITMO.informationSecurity.dto;

public record LoginRequest(
        String username,
        String password
) {
}
