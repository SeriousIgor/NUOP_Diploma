package com.diploma.models;

import java.math.BigInteger;

public class Client {
    private BigInteger clientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean isDeleted;

    public Client(BigInteger clientId, String firstName, String lastName, String phoneNumber, String email, boolean isDeleted) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
