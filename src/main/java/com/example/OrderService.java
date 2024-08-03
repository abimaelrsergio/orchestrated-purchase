package com.example;

import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderService {
    private final Set<Long> orders = new HashSet<>();

    public void doOrder(Long id) {
        orders.add(id);
    }

    public void undoOrder(Long id){
        orders.remove(id);
    }
}
