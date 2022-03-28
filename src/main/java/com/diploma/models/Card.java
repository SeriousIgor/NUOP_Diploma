package com.diploma.models;

import java.math.BigInteger;

public class Card {
    private BigInteger cardId;
    private Client client;
    private boolean isDiscount;
    private Integer discountPercentage;
    private Double bonuses;
    private boolean isDeleted;

    public Card(BigInteger cardId, Client client, boolean isDiscount, Integer discountPercentage, Double bonuses, boolean isDeleted) {
        this.cardId = cardId;
        this.client = client;
        this.isDiscount = isDiscount;
        this.discountPercentage = discountPercentage;
        this.bonuses = bonuses;
        this.isDeleted = isDeleted;
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
