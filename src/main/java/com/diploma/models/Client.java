package com.diploma.models;

import java.math.BigInteger;

public class Client {
    private BigInteger clientId;
    private String firstName;
    private String lastName;

    public Client(BigInteger clientId, String firstName, String lastName) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
