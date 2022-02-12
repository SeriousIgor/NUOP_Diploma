package com.diploma.models;

import java.math.BigInteger;

public class Card {
    private BigInteger cardId;
    private Client client;
    private boolean isDiscount;
    private int discountPercentage;
    private double bonuses;

    public Card(BigInteger cardId, Client client, boolean isDiscount, int discountPercentage, double bonuses) {
        this.cardId = cardId;
        this.client = client;
        this.isDiscount = isDiscount;
        this.discountPercentage = discountPercentage;
        this.bonuses = bonuses;
    }

    public BigInteger getCardId() {
        return cardId;
    }

    public void setCardId(BigInteger cardId) {
        this.cardId = cardId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }
}
