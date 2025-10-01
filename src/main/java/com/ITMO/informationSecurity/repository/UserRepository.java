package com.ITMO.informationSecurity.repository;

import com.ITMO.informationSecurity.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    Map<UUID, User> userMap = new HashMap<>();

    {
        userMap.put(UUID.randomUUID(), User.builder().username("a").password(bcrypt("qwerty1")).build());
        userMap.put(UUID.randomUUID(), User.builder().username("b").password(bcrypt("qwerty2")).build());
        userMap.put(UUID.randomUUID(), User.builder().username("c").password(bcrypt("qwerty3")).build());
    }

    private static String bcrypt(String raw) {
        return BCrypt.hashpw(raw, BCrypt.gensalt(12));
    }

    public Optional<User> findByUsername(String username) {
        if (username == null) return Optional.empty();
        return userMap.values().stream().filter(u -> username.equals(u.getUsername())).findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }
}
