package com.ITMO.informationSecurity.repository;

import com.ITMO.informationSecurity.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.apache.logging.log4j.util.Base64Util.encode;

public class UserRepository {


    Map<UUID, User> userMap = new HashMap<>();

    {
        userMap.put(UUID.randomUUID(), User.builder().username("a").password(encode("qwerty1")).build());
        userMap.put(UUID.randomUUID(), User.builder().username("b").password(encode("qwerty2")).build());
        userMap.put(UUID.randomUUID(), User.builder().username("c").password(encode("qwerty3")).build());
    }

    public Optional<User> findByUsername(String username) {
        if (username == null) return Optional.empty();
        return userMap.values().stream().filter(u -> username.equals(u.getUsername())).findFirst();
    }
}
