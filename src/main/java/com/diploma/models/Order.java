package com.diploma.models;

import com.diploma.models.enumeration.OrderStatus;

import java.math.BigInteger;

public class Order {
    private BigInteger orderId;
    private OrderStatus status;
    private Double price;
    private BigInteger userId;
    private BigInteger clientId;



    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }
}
