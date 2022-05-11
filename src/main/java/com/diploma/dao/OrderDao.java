package com.diploma.dao;

import com.diploma.models.Order;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

public interface OrderDao {
    Order getOrder(BigInteger orderId) throws SQLException;

    Order getOrder(Timestamp orderDate) throws SQLException;

    Collection<Order> getOrders() throws SQLException;

    Collection<Order> getOrders(String orderName) throws SQLException;

    Collection<Order> getOrders(BigInteger clientId) throws SQLException;

    Collection<Order> getAllOrders() throws SQLException;

    Boolean createOrder(Order order) throws SQLException;

    Boolean updateOrder(Order order) throws SQLException;

    Boolean deleteOrder(BigInteger orderId) throws SQLException;

    String GET_ORDER = "SELECT orderId, name, status, price, description, orderDate, endDate, o.userId, o.clientId, isCardUsed, o.isDeleted, " +
                            "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", client.email as \"client.email\", client.isDeleted as \"client.isDeleted\"," +
                            "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\", user.isDeleted as \"user.isDeleted\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE orderId = ";
    String GET_ORDER_BY_DATE = "SELECT orderId, name, status, price, description, orderDate, endDate, o.userId, o.clientId, isCardUsed, o.isDeleted, " +
                            "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", client.email as \"client.email\", client.isDeleted as \"client.isDeleted\", " +
                            "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\", user.isDeleted as \"user.isDeleted\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE orderDate = ";
    String GET_ORDERS = "SELECT orderId, name, status, price, description, orderDate, endDate, o.userId, o.clientId, isCardUsed, o.isDeleted, " +
                            "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", client.email as \"client.email\", client.isDeleted as \"client.isDeleted\", " +
                            "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\", user.isDeleted as \"user.isDeleted\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE o.isDeleted = 0";
    String GET_ORDERS_BY_NAME = "SELECT orderId, name, status, price, description, orderDate, endDate, o.userId, o.clientId, isCardUsed, o.isDeleted, " +
                        "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", client.email as \"client.email\", client.isDeleted as \"client.isDeleted\", " +
                        "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\", user.isDeleted as \"user.isDeleted\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE name like ";
    String GET_ORDERS_BY_CLIENT = "SELECT orderId, name, status, price, description, orderDate, endDate, o.userId, o.clientId, isCardUsed, o.isDeleted, " +
                        "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", client.email as \"client.email\", client.isDeleted as \"client.isDeleted\", " +
                        "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\", user.isDeleted as \"user.isDeleted\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId " +
                        "WHERE o.clientId = ";
    String GET_ALL_ORDERS = "SELECT orderId, name, status, price, description, orderDate, endDate, o.userId, o.clientId, isCardUsed, o.isDeleted, " +
                        "client.clientId as \"client.clientId\", client.firstName as \"client.firstName\", client.lastName as \"client.lastName\", client.phoneNumber as \"client.phoneNumber\", client.email as \"client.email\", client.isDeleted as \"client.isDeleted\", " +
                        "user.userId as \"user.userId\", user.username as \"user.username\", user.firstName as \"user.firstName\", user.lastName as \"user.lastName\", user.password as \"user.password\", user.isAdmin as \"user.isAdmin\", user.isDeleted as \"user.isDeleted\" " +
                        "FROM Orders o " +
                        "LEFT JOIN Client client ON o.clientId = client.clientId " +
                        "LEFT JOIN User user ON o.userId = user.userId";
    String CREATE_ORDER = "INSERT INTO Orders (name, status, price, description, orderDate, userId, clientId, isCardUsed) VALUES (";
    String UPDATE_ORDER = "UPDATE Orders SET ";
    String DELETE_ORDER = "UPDATE Orders SET isDeleted = 1 WHERE orderId = ";
}
