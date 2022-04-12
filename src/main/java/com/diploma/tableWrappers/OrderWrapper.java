package com.diploma.tableWrappers;

import com.diploma.models.Order;
import com.diploma.models.Service;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Collection;

public class OrderWrapper {
    private SimpleIntegerProperty orderId;
    private SimpleStringProperty name;
    private SimpleStringProperty status;
    private SimpleDoubleProperty price;
    private SimpleStringProperty description;
    private SimpleStringProperty orderDate;
    private UserWrapper user;
    private ClientWrapper client;
    private Collection<ServiceWrapper> services;
    private SimpleBooleanProperty isDeleted;

    public OrderWrapper(Order order){
        this.orderId = new SimpleIntegerProperty(order.getOrderId().intValue());
        this.name = new SimpleStringProperty(order.getName());
        this.status = new SimpleStringProperty(order.getStatus().toString());
        this.price = new SimpleDoubleProperty(order.getPrice());
        this.description = new SimpleStringProperty(order.getDescription());
        this.orderDate = new SimpleStringProperty(order.getOrderDate().toString());
        this.user = new UserWrapper(order.getUser());
        this.client = new ClientWrapper(order.getClient());

        ArrayList<ServiceWrapper> wrappedList = new ArrayList<>();
        for(Service service : order.getServices()){
            wrappedList.add(new ServiceWrapper(service));
        }
        this.services = wrappedList;
        this.isDeleted = new SimpleBooleanProperty(order.isDeleted());
    }
}
