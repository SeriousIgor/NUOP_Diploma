package com.diploma.dao.implementation;

import com.diploma.dao.OrderServiceBundleDao;
import com.diploma.models.OrderServiceBundle;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderServiceBundleDaoImplementation implements OrderServiceBundleDao {
    private Connection connection;
    private Statement stm;

    public OrderServiceBundleDaoImplementation(Connection connection, Statement stm) throws SQLException {
        this.connection = connection;
        this.stm = stm;
    }

    public OrderServiceBundleDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }

    @Override
    public OrderServiceBundle getOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLE + orderServiceBundleId);
        if(resultSet.next()){
            return buildOrderServiceBundle(resultSet);
        } else {
            throw new SQLException("OrderServiceBundle not found");
        }
    }

    @Override
    public Collection<OrderServiceBundle> getOrderServiceBundles() throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLES);
        return buildOrderServiceBundleList(resultSet);
    }

    @Override
    public Collection<OrderServiceBundle> getOrderServiceBundles(BigInteger orderId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLES_BY_ORDER + orderId);
        return buildOrderServiceBundleList(resultSet);
    }

    @Override
    public Collection<OrderServiceBundle> getOrderServiceBundles(BigInteger orderId, BigInteger serviceId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLES_BY_SERVICE_AND_ORDER + orderId + " AND serviceId = " + serviceId);
        return buildOrderServiceBundleList(resultSet);
    }

    @Override
    public Boolean createOrderServiceBundle(OrderServiceBundle orderServiceBundle) throws SQLException {
        String query = OrderServiceBundleDao.CREATE_ORDERSERVICEBUNDLE + "'" + orderServiceBundle.getOrderId() + "', '" + orderServiceBundle.getServiceId() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean updateOrderServiceBundle(OrderServiceBundle orderServiceBundle) throws SQLException {
        String query = OrderServiceBundleDao.UPDATE_ORDERSERVICEBUNDLE + "orderId = " + orderServiceBundle.getOrderId() + ", serviceId = " + orderServiceBundle.getServiceId() + " WHERE orderServiceBundleId = " + orderServiceBundle.getOrderServiceBundleId();
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean deleteOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException {
        String query = OrderServiceBundleDao.DELETE_ORDERSERVICEBUNDLE + orderServiceBundleId;
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean hardDeleteOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException {
        String query = OrderServiceBundleDao.HARD_DELETE_ORDERSERVICEBUNDLE + orderServiceBundleId;
        Boolean result = stm.executeUpdate(query) == 1;
        return  result;
    }

    private OrderServiceBundle buildOrderServiceBundle(ResultSet resultSet) throws SQLException {
        BigInteger orderServiceBundleId = BigInteger.valueOf(resultSet.getInt("orderServiceBundleId"));
        BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
        BigInteger serviceId = BigInteger.valueOf(resultSet.getInt("serviceId"));
        Boolean isDeleted = resultSet.getInt("isDeleted") == 1;
        OrderServiceBundle orderServiceBundle = new OrderServiceBundle(orderServiceBundleId, orderId, serviceId, isDeleted);

        return orderServiceBundle;
    }

    private Collection<OrderServiceBundle> buildOrderServiceBundleList(ResultSet resultSet) throws SQLException {
        Collection<OrderServiceBundle> orderServiceBundleList = new ArrayList<OrderServiceBundle>();
        if(resultSet.next()){
            do{
                orderServiceBundleList.add(buildOrderServiceBundle(resultSet));
            } while(resultSet.next());

            return orderServiceBundleList;
        } else {
            throw new SQLException("OrderServiceBundles not found");
        }
    }
}
