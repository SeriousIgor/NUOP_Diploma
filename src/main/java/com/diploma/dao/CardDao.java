package com.diploma.dao;

import com.diploma.models.Card;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface CardDao {
    Card getCard(BigInteger cardId) throws SQLException;

    Collection<Card> getCards() throws SQLException;

    Collection<Card> getCards(BigInteger clientId);

    Boolean createCard(Card card);

    Boolean updateCard(Card card);

    Boolean deleteCard(BigInteger id);

    String GET_CARD = "SELECT cardId, card.clientId, isDiscount, discountPercentage, bonuses, " +
                            "client.firstName, client.lastName, client.phoneNumber " +
                      "FROM Card card " +
                      "LEFT JOIN Client client ON card.clientId = client.clientId " +
                      "WHERE cardId = ";

    String GET_CARDS = "SELECT cardId, card.clientId, isDiscount, discountPercentage, bonuses, " +
                            "client.firstName, client.lastName, client.phoneNumber " +
                        "FROM Card card " +
                        "LEFT JOIN Client client ON card.clientId = client.clientId " +
                        "WHERE card.isDeleted = 0";
}
