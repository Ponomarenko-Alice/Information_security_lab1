package com.ITMO.informationSecurity.service;

import com.ITMO.informationSecurity.exceptions.UserNotFoundException;
import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    final UserRepository userRepository;
    public String authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден, зарегестируйтесь: " + username);
        }

        String token = UUID.randomUUID().toString();
        return token;
    }
}
