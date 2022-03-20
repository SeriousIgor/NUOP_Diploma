package com.diploma.dao;

import com.diploma.models.Order;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface OrderDao {
    Order getOrder(BigInteger orderId) throws SQLException;

    Collection<Order> getOrders() throws SQLException;

    Collection<Order> getOrders(String orderName) throws SQLException;

    Collection<Order> getOrders(BigInteger clientId) throws SQLException;

    Boolean createOrder(Order order) throws SQLException;

    Boolean updateOrder(Order order);

    Boolean deleteOrder(BigInteger orderId);

    String GET_ORDER = "SELECT orderId, name, status, price, description, orderDate, o.userId, o.clientId, " +
                            "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", " +
                            "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE orderId = ";
    String GET_ORDERS = "SELECT orderId, name, status, price, description, orderDate, o.userId, o.clientId, " +
                            "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", " +
                            "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE o.isDeleted = 0";
    String GET_ORDERS_BY_NAME = "SELECT orderId, name, status, price, description, orderDate, o.userId, o.clientId, " +
                        "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", " +
                        "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE name like ";
    String GET_ORDERS_BY_CLIENT = "SELECT orderId, name, status, price, description, orderDate, o.userId, o.clientId, " +
                        "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", " +
                        "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE o.clientId = ";
    String CREATE_ORDER = "INSERT INTO Orders (orderId, name, status, price, description, orderDate, userId, clientId) VALUES (";
    String UPDATE_ORDER = "UPDATE Orders SET ";
    String DELETE_ORDER = "";
}
