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
    private User user;
    private Client client;
    private Collection<Service> services;

    public Order(BigInteger orderId, String name, OrderStatus status, Double price, String description, Timestamp orderDate, User user, Client client, Collection<Service> services) {
        this.orderId = orderId;
        this.name = name;
        this.status = status;
        this.price = price;
        this.description = description;
        this.orderDate = orderDate;
        this.user = user;
        this.client = client;
        this.services = services;
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

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }
}
