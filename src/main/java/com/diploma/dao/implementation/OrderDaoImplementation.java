package com.diploma.dao.implementation;

import com.diploma.dao.OrderDao;
import com.diploma.models.*;
import com.diploma.models.enumeration.OrderStatus;

import java.math.BigInteger;
import java.sql.*;
import java.util.*;

public class OrderDaoImplementation implements OrderDao {
    private OrderServiceBundleDaoImplementation osbdi;
    private ServiceDaoImplementation sdi;
    private Connection connection;
    private Statement stm;

    public OrderDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
        osbdi = new OrderServiceBundleDaoImplementation(connection, stm);
        sdi = new ServiceDaoImplementation(connection, stm);
    }

    public OrderDaoImplementation(Connection connection, Statement stm) throws SQLException {
        this.connection = connection;
        this.stm = stm;
        osbdi = new OrderServiceBundleDaoImplementation(connection, stm);
        sdi = new ServiceDaoImplementation(connection, stm);
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

            Collection<OrderServiceBundle> osbList = osbdi.getOrderServiceBundles(orderId);
            Collection<Service> services = new ArrayList<Service>();
            for(OrderServiceBundle osb : osbList){
                Service service = sdi.getService(osb.getServiceId());
                services.add(service);
            }

            Order order = new Order(orderId, name, status, price, description, orderDate, user, client, services);
            return order;
        } else {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public Order getOrder(Timestamp orderDate) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderDao.GET_ORDER_BY_DATE + "'" + orderDate + "'");
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

            BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
            String name = resultSet.getString("name");
            OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
            Double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");


            Order order = new Order(orderId, name, status, price, description, orderDate, user, client, null);
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

                Collection<OrderServiceBundle> osbList = osbdi.getOrderServiceBundles(orderId);
                Collection<Service> services = new ArrayList<Service>();
                for(OrderServiceBundle osb : osbList){
                    Service service = sdi.getService(osb.getServiceId());
                    services.add(service);
                }

                Order order = new Order(orderId, name, status, price, description, orderDate, user, client, services);
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

                Collection<OrderServiceBundle> osbList = osbdi.getOrderServiceBundles(orderId);
                Collection<Service> services = new ArrayList<Service>();
                for(OrderServiceBundle osb : osbList){
                    Service service = sdi.getService(osb.getServiceId());
                    services.add(service);
                }

                Order order = new Order(orderId, name, status, price, description, orderDate, user, client, services);
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

                Collection<OrderServiceBundle> osbList = osbdi.getOrderServiceBundles(orderId);
                Collection<Service> services = new ArrayList<Service>();
                for(OrderServiceBundle osb : osbList){
                    Service service = sdi.getService(osb.getServiceId());
                    services.add(service);
                }

                Order order = new Order(orderId, name, status, price, description, orderDate, user, client, services);
                orderList.add(order);
            } while (resultSet.next());
            return orderList;
        } else {
            throw new SQLException("Orders not found");
        }
    }

    @Override
    public Boolean createOrder(Order order) throws SQLException {
        Timestamp currentDate = new Timestamp(new java.util.Date().getTime());
        Collection<Service> services = order.getServices();

        String query = OrderDao.CREATE_ORDER + "'" + order.getName() + "', '" + order.getStatus() + "', '" + order.getPrice() + "', '" + order.getDescription() + "', '" + currentDate + "', '" + order.getUser().getUserId() + "', '" + order.getClient().getClientId() + "')";
        Boolean orderCreationResult = stm.executeUpdate(query) == 1;
        order = getOrder(currentDate);

        Boolean orderServiceBundleCreationResult = true;
        for(Service service : services){
            OrderServiceBundle osb = new OrderServiceBundle(null, order.getOrderId(), service.getServiceId());
            if(!osbdi.createOrderServiceBundle(osb)){
                orderServiceBundleCreationResult = false;
            };
        }
        return orderCreationResult && orderServiceBundleCreationResult;
    }

    @Override
    public Boolean updateOrder(Order order) throws SQLException {
        Collection<Service> oldServices = getOrder(order.getOrderId()).getServices();
        Collection<Service> newServices = order.getServices();
        String query = OrderDao.UPDATE_ORDER + "name ='" + order.getName() + "', status = '" + order.getStatus() + "', price = '" + order.getPrice() + "', description = '" + order.getDescription() + "', orderDate = '" + order.getOrderDate() + "', userId = " + order.getUser().getUserId() + ", clientId = " + order.getClient().getClientId() + " WHERE orderId = " + order.getOrderId();
        Boolean orderUpdateResult = stm.executeUpdate(query) == 1;
        for(Service oldS : oldServices){
            for(Service newS : newServices){
                if(oldS.getServiceId().equals(newS.getServiceId())){
                    oldServices.remove(oldS);
                    newServices.remove(newS);
                    break;
                }
            }
            if(oldServices.size() == 0) {
                break;
            }
        }
        Boolean orderServiceBundleUpdateResult = true;
        if(!oldServices.isEmpty()){
            for(Service service : oldServices){
                Collection<OrderServiceBundle> oldBundles = osbdi.getOrderServiceBundles(order.getOrderId(), service.getServiceId());
                for(OrderServiceBundle osb : oldBundles){
                    if(!osbdi.hardDeleteOrderServiceBundle(osb.getOrderServiceBundleId())){
                        orderServiceBundleUpdateResult = false;
                    };
                }
            }
        }
        if(!newServices.isEmpty()){
            for(Service service : newServices){
                OrderServiceBundle newBundle = new OrderServiceBundle(null, order.getOrderId(), service.getServiceId());
                if(!osbdi.createOrderServiceBundle(newBundle)){
                    orderServiceBundleUpdateResult = false;
                }
            }
        }
        return orderUpdateResult && orderServiceBundleUpdateResult;
    }

    @Override
    public Boolean deleteOrder(BigInteger orderId) throws SQLException {
        String query = DELETE_ORDER + orderId;
        Boolean orderDeletionResult = stm.executeUpdate(query) == 1;
        Boolean orderServiceBundleDeletionResult = true;
        for(OrderServiceBundle osb : osbdi.getOrderServiceBundles(orderId)){
            if(!osbdi.deleteOrderServiceBundle(osb.getOrderServiceBundleId())){
                orderServiceBundleDeletionResult = false;
            }
        }
        return orderDeletionResult && orderServiceBundleDeletionResult;
    }
}
