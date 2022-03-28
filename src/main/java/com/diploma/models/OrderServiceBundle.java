package com.diploma.models;

import java.math.BigInteger;

public class OrderServiceBundle {
    private BigInteger orderServiceBundleId;
    private BigInteger orderId;
    private BigInteger serviceId;
    private boolean isDeleted;

    public OrderServiceBundle(BigInteger orderServiceBundleId, BigInteger orderId, BigInteger serviceId, boolean isDeleted) {
        this.orderServiceBundleId = orderServiceBundleId;
        this.orderId = orderId;
        this.serviceId = serviceId;
        this.isDeleted = isDeleted;
    }

    public BigInteger getOrderServiceBundleId() {
        return orderServiceBundleId;
    }

    public void setOrderServiceBundleId(BigInteger orderServiceBundleId) {
        this.orderServiceBundleId = orderServiceBundleId;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getServiceId() {
        return serviceId;
    }

    public void setServiceId(BigInteger serviceId) {
        this.serviceId = serviceId;
    }
}
