package com.ITMO.informationSecurity;

import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserInit {

    private final UserRepository users;

    @Bean
    CommandLineRunner seedUsers() {
        return args -> {
            seed("alice", "qwerty1", "ACTIVE");
            seed("bob", "qwerty2", "LOCKED");
            seed("charlie", "qwerty3", "ACTIVE");
        };
    }

    private void seed(String username, String rawPassword, String status) {
        if (users.findByUsername(username).isPresent()) return;

        String hash = BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));

        User u = User.builder()
                .username(username)
                .password(hash)
                .status(status)
                .build();
        users.save(u);
    }
}