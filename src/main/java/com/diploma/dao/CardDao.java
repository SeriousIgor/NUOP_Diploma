package com.diploma.dao;

import com.diploma.models.Card;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface CardDao {
    Card getCard(BigInteger cardId) throws SQLException;

    Collection<Card> getCards() throws SQLException;

    Collection<Card> getCards(BigInteger clientId) throws SQLException;

    Boolean createCard(Card card) throws SQLException;

    Boolean updateCard(Card card) throws SQLException;

    Boolean deleteCard(BigInteger cardId) throws SQLException;

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
    String GET_CARDS_BY_CLIENT = "SELECT cardId, card.clientId, isDiscount, discountPercentage, bonuses, " +
                        "client.firstName, client.lastName, client.phoneNumber " +
                        "FROM Card card " +
                        "LEFT JOIN Client client ON card.clientId = client.clientId " +
                        "WHERE card.isDeleted = 0 AND card.clientId = ";
    String CREATE_CARD = "INSERT INTO Card (clientId, isDiscount, discountPercentage, bonuses) VALUES (";
    String UPDATE_CARD = "UPDATE Card SET ";
    String DELETE_CARD = "UPDATE Card SET isDeleted = 1 WHERE cardId = ";
}
