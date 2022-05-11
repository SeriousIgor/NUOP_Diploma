package com.diploma.models;

import com.diploma.models.enumeration.OrderStatus;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;

public class Order {
    private BigInteger orderId;
    private String name;
    private OrderStatus status;
    private Double price;
    private String description;
    private Timestamp orderDate;
    private Timestamp endDate;
    private User user;
    private Client client;
    private boolean isCardUsed;
    private Collection<Service> services;
    private boolean isDeleted;

    public Order(BigInteger orderId, String name, OrderStatus status, Double price, String description, Timestamp orderDate, Timestamp endDate, User user, Client client, boolean isCardUsed, Collection<Service> services, boolean isDeleted) {
        this.orderId = orderId;
        this.name = name;
        this.status = status;
        this.price = price;
        this.description = description;
        this.orderDate = orderDate;
        this.endDate = endDate;
        this.user = user;
        this.client = client;
        this.isCardUsed = isCardUsed;
        this.services = services;
        this.isDeleted = isDeleted;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isCardUsed() {
        return isCardUsed;
    }

    public void setCardUsed(boolean cardUsed) {
        isCardUsed = cardUsed;
    }

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
