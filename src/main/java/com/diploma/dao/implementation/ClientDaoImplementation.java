package com.diploma.dao.implementation;

import com.diploma.dao.ClientDao;
import com.diploma.models.Client;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClientDaoImplementation implements ClientDao {
    private Connection connection;
    private Statement stm;

    public ClientDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }

    public ClientDaoImplementation(Connection connection) throws SQLException {
        this.connection = connection;
        this.stm = connection.createStatement();
    }

    @Override
    public Client getClient(BigInteger clientId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(ClientDao.GET_CLIENT + clientId);
        if (resultSet.next()) {
            return buildClient(resultSet);
        } else {
            throw new SQLException("Client not found");
        }
    }

    @Override
    public Collection<Client> getClients() throws SQLException {
        ResultSet resultSet = stm.executeQuery(ClientDao.GET_CLIENTS);
        return buildClientList(resultSet);
    }

    @Override
    public Collection<Client> getClients(String name) throws SQLException {
        String query = ClientDao.GET_CLIENTS_BY_NAME + "'%" + name + "%' OR lastName like '%" + name + "%')";
        ResultSet resultSet = stm.executeQuery(query);
        return buildClientList(resultSet);
    }

    @Override
    public Collection<Client> getClientsWithoutCards() throws SQLException {
        ResultSet resultSet = stm.executeQuery(ClientDao.GET_CLIENTS_WITHOUT_CARD);
        return buildClientList(resultSet);
    }

    @Override
    public Boolean createCliend(Client client) throws SQLException {
        String query = ClientDao.CREATE_CLIENT + "'" + client.getFirstName() + "', '" + client.getLastName() + "', '" + client.getPhoneNumber() + "', '" + client.getEmail() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean updateClient(Client client) throws SQLException {
        String query = ClientDao.UPDATE_CLIENT + "firstName = '" + client.getFirstName() + "', lastName = '" + client.getLastName() + "', phoneNumber = '" + client.getPhoneNumber() + "', email = '" + client.getEmail() + "' WHERE clientId = " + client.getClientId();
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean deleteClient(BigInteger clientId) throws SQLException {
        Boolean result = stm.executeUpdate(ClientDao.DELETE_CLIENT + clientId) == 1;
        return result;
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {
        BigInteger clientId = BigInteger.valueOf(resultSet.getInt("clientId"));
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String phoneNumber = resultSet.getString("phoneNumber");
        String email = resultSet.getString("email");
        Boolean isDeleted = resultSet.getInt("isDeleted") == 1;
        Client client = new Client(clientId, firstName, lastName, phoneNumber, email, isDeleted);

        return client;
    }

    private Collection<Client> buildClientList(ResultSet resultSet) throws SQLException {
        Collection<Client> clientList = new ArrayList<Client>();
        if(resultSet.next()){
            do {
                clientList.add(buildClient(resultSet));
            } while (resultSet.next());
            return clientList;
        } else {
            throw new SQLException("Clients not found");
        }
    }
}
