package com.diploma.models;

import java.math.BigInteger;

public class Service {
    private BigInteger serviceId;
    private String name;
    private Double price;
    private String description;
    private boolean isDeleted;

    public Service(BigInteger serviceId, String name, Double price, String description, boolean isDeleted) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public BigInteger getServiceId() {
        return serviceId;
    }

    public void setServiceId(BigInteger serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
