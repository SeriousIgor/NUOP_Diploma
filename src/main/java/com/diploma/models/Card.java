package com.diploma.models;

import java.math.BigInteger;

public class Card {
    private BigInteger cardId;
    private boolean isDiscount;
    private int discountPercentage;
    private double bonuses;

    public Card(BigInteger cardId, boolean isDiscount, int discountPercentage, double bonuses) {
        this.cardId = cardId;
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
