package com.diploma.tableWrappers;

import com.diploma.models.Client;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClientWrapper {
    private SimpleIntegerProperty clientId;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleBooleanProperty isDeleted;

    public ClientWrapper(Client client){
        this.clientId = new SimpleIntegerProperty(client.getClientId().intValue());
        this.firstName = new SimpleStringProperty(client.getFirstName());
        this.lastName = new SimpleStringProperty(client.getLastName());
        this.phoneNumber = new SimpleStringProperty(client.getPhoneNumber());
        this.isDeleted = new SimpleBooleanProperty(client.isDeleted());
    }

    public int getClientId() {
        return clientId.get();
    }

    public SimpleIntegerProperty clientIdProperty() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId.set(clientId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
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
