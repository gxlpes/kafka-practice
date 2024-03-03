package com.gxlpes.ecommerce.models;

import java.math.BigDecimal;

public class Order {

    private final String userId;
    private final String orderId;
    private final double amount;

    public Order(String userId, String orderId, double amount) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
