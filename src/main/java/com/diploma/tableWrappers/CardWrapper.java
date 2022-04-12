package com.diploma.tableWrappers;

import com.diploma.models.Card;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CardWrapper {
    private SimpleIntegerProperty cardId;
    private ClientWrapper clientWrapper;
    private SimpleBooleanProperty isDiscount;
    private SimpleIntegerProperty discountPercentage;
    private SimpleDoubleProperty bonuses;
    private SimpleBooleanProperty isDeleted;

    public CardWrapper(Card card){
        this.cardId = new SimpleIntegerProperty(card.getCardId().intValue());
        this.clientWrapper = new ClientWrapper(card.getClient());
        this.isDiscount = new SimpleBooleanProperty(card.isDiscount());
        this.discountPercentage = new SimpleIntegerProperty(card.getDiscountPercentage());
        this.bonuses = new SimpleDoubleProperty(card.getBonuses());
        this.isDeleted = new SimpleBooleanProperty(card.isDeleted());
    }

    public int getCardId() {
        return cardId.get();
    }

    public SimpleIntegerProperty cardIdProperty() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId.set(cardId);
    }

    public ClientWrapper getClientWrapper() {
        return clientWrapper;
    }

    public void setClientWrapper(ClientWrapper clientWrapper) {
        this.clientWrapper = clientWrapper;
    }

    public boolean isIsDiscount() {
        return isDiscount.get();
    }

    public SimpleBooleanProperty isDiscountProperty() {
        return isDiscount;
    }

    public void setIsDiscount(boolean isDiscount) {
        this.isDiscount.set(isDiscount);
    }

    public int getDiscountPercentage() {
        return discountPercentage.get();
    }

    public SimpleIntegerProperty discountPercentageProperty() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage.set(discountPercentage);
    }

    public double getBonuses() {
        return bonuses.get();
    }

    public SimpleDoubleProperty bonusesProperty() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses.set(bonuses);
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
