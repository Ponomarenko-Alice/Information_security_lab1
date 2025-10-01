package com.ITMO.informationSecurity.dto;

public record LoginRequest(
        String login,
        String password
) {
}
