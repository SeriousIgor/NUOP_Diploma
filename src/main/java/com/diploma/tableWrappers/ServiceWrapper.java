package com.diploma.tableWrappers;

import com.diploma.models.Service;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ServiceWrapper {
    private SimpleIntegerProperty serviceId;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleStringProperty description;
    private SimpleBooleanProperty isDeleted;

    public ServiceWrapper(Service service) {
        this.serviceId = new SimpleIntegerProperty(service.getServiceId().intValue());
        this.name = new SimpleStringProperty(service.getName());
        this.price = new SimpleDoubleProperty(service.getPrice());
        this.description = new SimpleStringProperty(service.getDescription());
        this.isDeleted = new SimpleBooleanProperty(service.isDeleted());
    }

    public int getServiceId() {
        return serviceId.get();
    }

    public SimpleIntegerProperty serviceIdProperty() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId.set(serviceId);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isIsDeleted() {
        return isDeleted.get();
    }

    public SimpleBooleanProperty isDeletedProperty() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted.set(isDeleted);
    }
}
