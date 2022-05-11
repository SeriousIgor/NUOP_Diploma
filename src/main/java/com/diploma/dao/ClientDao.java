package com.diploma.dao;

import com.diploma.models.Client;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface ClientDao {
    Client getClient(BigInteger clientId) throws SQLException;

    Collection<Client> getClients() throws SQLException;

    Collection<Client> getClients(String name) throws SQLException;

    Collection<Client> getClientsWithoutCards() throws SQLException;

    Boolean createCliend(Client client) throws SQLException;

    Boolean updateClient(Client client) throws SQLException;

    Boolean deleteClient(BigInteger clientId) throws SQLException;

    String GET_CLIENT = "SELECT clientId, firstName, lastName, phoneNumber, email, isDeleted FROM Client WHERE clientId = ";

    String GET_CLIENTS = "SELECT clientId, firstName, lastName, phoneNumber, email, isDeleted FROM Client WHERE isDeleted = 0";

    String GET_CLIENTS_BY_NAME = "SELECT clientId, firstName, lastName, phoneNumber, email, isDeleted FROM Client WHERE (isDeleted = 0) AND (firstname like ";

    String GET_CLIENTS_WITHOUT_CARD = "SELECT Client.clientId, Client.firstName, Client.lastName, Client.phoneNumber, Client.email, Client.isDeleted FROM Client WHERE NOT EXISTS (SELECT cardId FROM Card WHERE Client.clientId = Card.clientId AND Card.isDeleted = 0)";

    String CREATE_CLIENT = "INSERT INTO Client (firstName, lastName, phoneNumber, email) VALUES (";

    String UPDATE_CLIENT = "UPDATE CLIENT SET ";

    String DELETE_CLIENT = "UPDATE CLIENT SET isDeleted = 1 WHERE clientId = ";
}
