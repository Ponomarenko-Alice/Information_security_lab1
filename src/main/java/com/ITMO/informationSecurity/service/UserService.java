package com.ITMO.informationSecurity.service;

import com.ITMO.informationSecurity.model.User;
import com.ITMO.informationSecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + username));
    }

    public List<User> search(String q) {
        return repo.searchByUsername(q == null ? "" : q);
    }
}

