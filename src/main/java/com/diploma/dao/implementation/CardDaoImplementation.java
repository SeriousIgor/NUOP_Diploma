package com.diploma.dao.implementation;

import com.diploma.dao.CardDao;
import com.diploma.models.Card;
import com.diploma.models.Client;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CardDaoImplementation implements CardDao {
    private Connection connection;
    private Statement stm;

    public CardDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }

    @Override
    public Card getCard(BigInteger cardId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(CardDao.GET_CARD + cardId);
        Card card = null;
        if (resultSet.next()){
            Boolean isDiscount = resultSet.getInt("isDiscount") == 1;
            Integer discountPercentage = resultSet.getInt("discountPercentage");
            Double bonuses = resultSet.getDouble("bonuses");
            BigInteger clientId = BigInteger.valueOf(resultSet.getInt("clientId"));
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String phoneNumber = resultSet.getString("phoneNumber");
            Client client = new Client(clientId, firstName, lastName, phoneNumber);
            card = new Card(cardId, client, isDiscount, discountPercentage, bonuses);
            return card;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Collection<Card> getCards() throws SQLException {
        ResultSet resultSet = stm.executeQuery(CardDao.GET_CARDS);
        Collection<Card> cardList = new ArrayList<Card>();
        if (resultSet.next()){
            do{
                BigInteger cardId = BigInteger.valueOf(resultSet.getInt("cardId"));
                Boolean isDiscount = resultSet.getInt("isDiscount") == 1;
                Integer discountPercentage = resultSet.getInt("discountPercentage");
                Double bonuses = resultSet.getDouble("bonuses");
                BigInteger clientId = BigInteger.valueOf(resultSet.getInt("clientId"));
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                Client client = new Client(clientId, firstName, lastName, phoneNumber);
                Card card = new Card(cardId, client, isDiscount, discountPercentage, bonuses);
                cardList.add(card);
            } while(resultSet.next());

            return cardList;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Collection<Card> getCards(BigInteger clientId) {
        return null;
    }

    @Override
    public Boolean createCard(Card card) {
        return null;
    }

    @Override
    public Boolean updateCard(Card card) {
        return null;
    }

    @Override
    public Boolean deleteCard(BigInteger id) {
        return null;
    }
}
