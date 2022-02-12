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

    @Override
    public Client getClient(BigInteger clientId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(ClientDao.GET_CLIENT + clientId);
        Client client = null;
        if (resultSet.next()){
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String phoneNumber = resultSet.getString("phoneNumber");
            client = new Client(clientId, firstName, lastName, phoneNumber);
            return client;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Collection<Client> getClients() throws SQLException {
        ResultSet resultSet = stm.executeQuery(ClientDao.GET_CLIENTS);
        ArrayList<Client> clientList = new ArrayList<Client>();
        if(resultSet.next()){
            do {
                BigInteger clientId = BigInteger.valueOf(resultSet.getInt("clientId"));
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                Client client = new Client(clientId, firstName, lastName, phoneNumber);
                clientList.add(client);
            } while (resultSet.next());
            return clientList;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Collection<Client> getClients(String name) throws SQLException {
        String partOfQuery = String.join(name.toLowerCase(), "(firstName like '%", "%' OR lastName like '%", "%')");
        ResultSet resultSet = stm.executeQuery(ClientDao.GET_CLIENTS_BY_NAME + partOfQuery);
        ArrayList<Client> clientList = new ArrayList<Client>();
        if(resultSet.next()){
            do {
                BigInteger clientId = BigInteger.valueOf(resultSet.getInt("clientId"));
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                Client client = new Client(clientId, firstName, lastName, phoneNumber);
                clientList.add(client);
            } while (resultSet.next());
            return clientList;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Boolean createCliend(Client client) throws SQLException {
        String query = ClientDao.CREATE_CLIENT + "'" + client.getFirstName() + "', '" + client.getLastName() + "', '" + client.getPhoneNubmer() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean updateClient(Client client) throws SQLException {
        String query = ClientDao.UPDATE_CLIENT + "firstName = '" + client.getFirstName() + "', lastName = '" + client.getLastName() + "', phoneNumber = '" + client.getPhoneNubmer() + "' WHERE clientId = " + client.getClientId();
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean deleteClient(BigInteger clientId) throws SQLException {
        Boolean result = stm.executeUpdate(ClientDao.DELETE_CLIENT + clientId) == 1;
        return result;
    }
}
