package com.ITMO.informationSecurity;

import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserInit {

    CommandLineRunner seedUsers(UserRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                User a = User.builder().username("alice").password(BCrypt.hashpw("qwerty1", BCrypt.gensalt(12))).build();
                User b = User.builder().username("bob").password(BCrypt.hashpw("qwerty2", BCrypt.gensalt(12))).build();
                User c = User.builder().username("carol").password(BCrypt.hashpw("qwerty3", BCrypt.gensalt(12))).build();
                repo.saveAll(List.of(a, b, c));
            }
        };
    }
}