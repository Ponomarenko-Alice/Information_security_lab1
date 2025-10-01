package com.ITMO.informationSecurity.service;

import com.ITMO.informationSecurity.exceptions.AuthException;
import com.ITMO.informationSecurity.exceptions.UserNotFoundException;
import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    final UserRepository userRepository;
    private final JwtService jwtService;

    public String authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден, зарегестируйтесь: " + username);
        }

        if (!BCrypt.checkpw(password, user.get().getPassword())) {
            throw new AuthException("Неправильные креды");
        }

        return jwtService.generateToken(username);
    }
}
