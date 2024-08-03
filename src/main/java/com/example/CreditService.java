package com.example;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditService {

    private int creditTotal;
    private final Map<Long, Integer> orderValue = new HashMap<>();

    public CreditService() {
        this.creditTotal = 100;
    }

    public void newOrderValue(Long orderId, int value) {
        if (value > creditTotal) {
            throw new IllegalStateException("insufficient funds");
        }
        creditTotal = creditTotal  - value;
        orderValue.put(orderId, value);
    }

    public void cancelOrderValue(Long id)  {
        creditTotal = creditTotal + orderValue.get(id);
        orderValue.remove(id);
    }

    public int getCreditTotal() {
        return creditTotal;
    }
}
