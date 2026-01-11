package com.example.practice.user;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final Map<String, AppUser> users = new ConcurrentHashMap<>();

    public Optional<AppUser> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public AppUser save(AppUser user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }
}

