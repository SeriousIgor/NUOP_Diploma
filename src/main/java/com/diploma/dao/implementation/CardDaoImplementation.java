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

    public CardDaoImplementation(Connection connection, Statement stm) throws SQLException {
        this.connection = connection;
        this.stm = stm;
    }

    @Override
    public Card getCard(BigInteger cardId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(CardDao.GET_CARD + cardId);
        return buildCard(resultSet);
    }

    @Override
    public Collection<Card> getCards() throws SQLException {
        ResultSet resultSet = stm.executeQuery(CardDao.GET_CARDS);
        return buildCardList(resultSet);
    }

    @Override
    public Collection<Card> getCards(BigInteger clientId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(CardDao.GET_CARDS_BY_CLIENT + clientId);
        return buildCardList(resultSet);
    }

    @Override
    public Boolean createCard(Card card) throws SQLException {
        Integer isDiscount = card.isDiscount() ? 1 : 0;
        String query = CardDao.CREATE_CARD + " '" + card.getClient().getClientId() + "', '" + isDiscount + "', '" + card.getDiscountPercentage() + "', '" + card.getBonuses() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean updateCard(Card card) throws SQLException {
        Integer isDiscount = card.isDiscount() ? 1 : 0;
        String query = CardDao.UPDATE_CARD + "clientId = '" + card.getClient().getClientId() + "', isDiscount = '" + isDiscount + "', discountPercentage = '" + card.getDiscountPercentage() + "', bonuses = '" + card.getBonuses() + "' WHERE cardId = '" + card.getCardId() + "'";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean deleteCard(BigInteger cardId) throws SQLException {
        Boolean result = stm.executeUpdate(CardDao.DELETE_CARD + cardId) == 1;
        return result;
    }

    private Card buildCard(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            BigInteger clientId = BigInteger.valueOf(resultSet.getInt("clientId"));
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String phoneNumber = resultSet.getString("phoneNumber");
            Boolean clientIsDeleted = resultSet.getInt("client.isDeleted") == 1;
            Client client = new Client(clientId, firstName, lastName, phoneNumber, clientIsDeleted);

            BigInteger cardId = BigInteger.valueOf(resultSet.getInt("cardId"));
            Boolean isDiscount = resultSet.getInt("isDiscount") == 1;
            Integer discountPercentage = resultSet.getInt("discountPercentage");
            Double bonuses = resultSet.getDouble("bonuses");
            Boolean cardIsDeleted = resultSet.getInt("isDeleted") == 1;
            Card card = new Card(cardId, client, isDiscount, discountPercentage, bonuses, cardIsDeleted);

            return card;
        } else {
            throw new SQLException("No records found");
        }
    }

    private Collection<Card> buildCardList(ResultSet resultSet) throws SQLException {
        Collection<Card> cardList = new ArrayList<>();
        if (resultSet.next()){
        do{
            cardList.add(buildCard(resultSet));
        } while(resultSet.next());
            return cardList;
        } else {
            throw new SQLException("No records found");
        }
    }
}
