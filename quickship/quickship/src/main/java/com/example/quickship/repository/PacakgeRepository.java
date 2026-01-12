package com.example.quickship.repository;

import java.util.ArrayList;
import java.util.*;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.quickship.models.deliverypackage;

@Repository
public class PacakgeRepository {
    
    private final Map<String, deliverypackage> store = new ConcurrentHashMap<>();
    
    public deliverypackage save(deliverypackage delivery){
        store.put(delivery.getId(), delivery);
        return delivery;
    }

    public Optional<deliverypackage> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<deliverypackage> findAll() {
        return new ArrayList<>(store.values());
    }
}
