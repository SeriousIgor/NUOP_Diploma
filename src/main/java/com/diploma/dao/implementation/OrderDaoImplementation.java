package com.diploma.dao.implementation;

import com.diploma.dao.OrderDao;
import com.diploma.models.Client;
import com.diploma.models.Order;
import com.diploma.models.User;
import com.diploma.models.enumeration.OrderStatus;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDaoImplementation implements OrderDao {
    private Connection connection;
    private Statement stm;

    public OrderDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }
    @Override
    public Order getOrder(BigInteger orderId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderDao.GET_ORDER + orderId);
        if(resultSet.next()){
            BigInteger clientId = BigInteger.valueOf(resultSet.getInt("client.clientId"));
            String clientFirstName = resultSet.getString("client.firstName");
            String clientLastName = resultSet.getString("client.lastName");
            String clientPhoneNumber = resultSet.getString("client.phoneNumber");
            Client client = new Client(clientId, clientFirstName, clientLastName, clientPhoneNumber);

            BigInteger userId = BigInteger.valueOf(resultSet.getInt("user.userID"));
            String userUsername = resultSet.getString("user.username");
            String userFirstName = resultSet.getString("user.firstName");
            String userLastName = resultSet.getString("user.lastName");
            String userPassword = resultSet.getString("user.password");
            Boolean userIsAdmin = resultSet.getInt("user.isAdmin") == 1;
            User user = new User(userId, userUsername, userFirstName, userLastName, userPassword, userIsAdmin);

            String name = resultSet.getString("name");
            OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
            Double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            Timestamp orderDate = resultSet.getTimestamp("orderDate");
            Order order = new Order(orderId, name, status, price, description, orderDate, user, client);
            return order;
        } else {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public Collection<Order> getOrders() throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderDao.GET_ORDERS);
        Collection<Order> orderList = new ArrayList<>();
        if(resultSet.next()){
            do {
                BigInteger clientId = BigInteger.valueOf(resultSet.getInt("client.clientId"));
                String clientFirstName = resultSet.getString("client.firstName");
                String clientLastName = resultSet.getString("client.lastName");
                String clientPhoneNumber = resultSet.getString("client.phoneNumber");
                Client client = new Client(clientId, clientFirstName, clientLastName, clientPhoneNumber);

                BigInteger userId = BigInteger.valueOf(resultSet.getInt("user.userID"));
                String userUsername = resultSet.getString("user.username");
                String userFirstName = resultSet.getString("user.firstName");
                String userLastName = resultSet.getString("user.lastName");
                String userPassword = resultSet.getString("user.password");
                Boolean userIsAdmin = resultSet.getInt("user.isAdmin") == 1;
                User user = new User(userId, userUsername, userFirstName, userLastName, userPassword, userIsAdmin);

                BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
                String name = resultSet.getString("name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                Double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                Order order = new Order(orderId, name, status, price, description, orderDate, user, client);
                orderList.add(order);
            } while (resultSet.next());
            return orderList;
        } else {
            throw new SQLException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(String orderName) throws SQLException {
        if(orderName == null){
            orderName = "'%%'";
        } else {
            orderName = new StringBuilder().append("'%").append(orderName).append("%'").toString();
        }
        ResultSet resultSet = stm.executeQuery(OrderDao.GET_ORDERS_BY_NAME + orderName);
        Collection<Order> orderList = new ArrayList<>();
        if(resultSet.next()){
            do {
                BigInteger clientId = BigInteger.valueOf(resultSet.getInt("client.clientId"));
                String clientFirstName = resultSet.getString("client.firstName");
                String clientLastName = resultSet.getString("client.lastName");
                String clientPhoneNumber = resultSet.getString("client.phoneNumber");
                Client client = new Client(clientId, clientFirstName, clientLastName, clientPhoneNumber);

                BigInteger userId = BigInteger.valueOf(resultSet.getInt("user.userID"));
                String userUsername = resultSet.getString("user.username");
                String userFirstName = resultSet.getString("user.firstName");
                String userLastName = resultSet.getString("user.lastName");
                String userPassword = resultSet.getString("user.password");
                Boolean userIsAdmin = resultSet.getInt("user.isAdmin") == 1;
                User user = new User(userId, userUsername, userFirstName, userLastName, userPassword, userIsAdmin);

                BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
                String name = resultSet.getString("name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                Double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                Order order = new Order(orderId, name, status, price, description, orderDate, user, client);
                orderList.add(order);
            } while (resultSet.next());
            return orderList;
        } else {
            throw new SQLException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(BigInteger clientId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderDao.GET_ORDERS_BY_CLIENT + clientId);
        Collection<Order> orderList = new ArrayList<>();
        if(resultSet.next()){
            do {
                String clientFirstName = resultSet.getString("client.firstName");
                String clientLastName = resultSet.getString("client.lastName");
                String clientPhoneNumber = resultSet.getString("client.phoneNumber");
                Client client = new Client(clientId, clientFirstName, clientLastName, clientPhoneNumber);

                BigInteger userId = BigInteger.valueOf(resultSet.getInt("user.userID"));
                String userUsername = resultSet.getString("user.username");
                String userFirstName = resultSet.getString("user.firstName");
                String userLastName = resultSet.getString("user.lastName");
                String userPassword = resultSet.getString("user.password");
                Boolean userIsAdmin = resultSet.getInt("user.isAdmin") == 1;
                User user = new User(userId, userUsername, userFirstName, userLastName, userPassword, userIsAdmin);

                BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
                String name = resultSet.getString("name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                Double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                Order order = new Order(orderId, name, status, price, description, orderDate, user, client);
                orderList.add(order);
            } while (resultSet.next());
            return orderList;
        } else {
            throw new SQLException("Orders not found");
        }
    }

    @Override
    public Boolean createOrder(Order order) throws SQLException {
        String query = CREATE_ORDER + "'" + order.getOrderId() + "', '" + order.getName() + "', '" + order.getStatus() + "', '" + order.getPrice() + "', '" + order.getDescription() + "', '" + order.getOrderDate().toString() + "', '" + order.getUser().getUserId() + "', '" + order.getClient().getClientId() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean updateOrder(Order order) {
        return null;
    }

    @Override
    public Boolean deleteOrder(BigInteger orderId) {
        return null;
    }
}
