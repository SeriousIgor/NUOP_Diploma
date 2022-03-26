package com.diploma.dao.implementation;

import com.diploma.dao.OrderServiceBundleDao;
import com.diploma.models.OrderServiceBundle;
import com.diploma.models.Service;

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

    @Override
    public OrderServiceBundle getOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLE + orderServiceBundleId);
        if(resultSet.next()){
            BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
            BigInteger serviceId = BigInteger.valueOf(resultSet.getInt("serviceId"));
            OrderServiceBundle orderServiceBundle = new OrderServiceBundle(orderServiceBundleId, orderId, serviceId);
            return orderServiceBundle;
        } else {
            throw new SQLException("OrderServiceBundle not found");
        }
    }

    @Override
    public Collection<OrderServiceBundle> getOrderServiceBundles() throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLES);
        Collection<OrderServiceBundle> orderServiceBundleList = new ArrayList<OrderServiceBundle>();
        if(resultSet.next()){
            do{
                BigInteger orderServiceBundleId = BigInteger.valueOf(resultSet.getInt("orderServiceBundleId"));
                BigInteger orderId = BigInteger.valueOf(resultSet.getInt("orderId"));
                BigInteger serviceId = BigInteger.valueOf(resultSet.getInt("serviceId"));
                OrderServiceBundle orderServiceBundle = new OrderServiceBundle(orderServiceBundleId, orderId, serviceId);
                orderServiceBundleList.add(orderServiceBundle);
            } while(resultSet.next());

            return orderServiceBundleList;
        } else {
            throw new SQLException("OrderServiceBundles not found");
        }
    }

    @Override
    public Collection<OrderServiceBundle> getOrderServiceBundles(BigInteger orderId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLES_BY_ORDER + orderId);
        Collection<OrderServiceBundle> orderServiceBundleList = new ArrayList<OrderServiceBundle>();
        if(resultSet.next()){
            do{
                BigInteger orderServiceBundleId = BigInteger.valueOf(resultSet.getInt("orderServiceBundleId"));
                BigInteger serviceId = BigInteger.valueOf(resultSet.getInt("serviceId"));
                OrderServiceBundle orderServiceBundle = new OrderServiceBundle(orderServiceBundleId, orderId, serviceId);
                orderServiceBundleList.add(orderServiceBundle);
            } while(resultSet.next());

            return orderServiceBundleList;
        } else {
            throw new SQLException("OrderServiceBundles not found");
        }
    }

    @Override
    public Collection<OrderServiceBundle> getOrderServiceBundles(BigInteger orderId, BigInteger serviceId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(OrderServiceBundleDao.GET_ORDERSERVICEBUNDLES_BY_SERVICE_AND_ORDER + orderId + " AND serviceId = " + serviceId);
        Collection<OrderServiceBundle> orderServiceBundleList = new ArrayList<OrderServiceBundle>();
        if(resultSet.next()){
            do{
                BigInteger orderServiceBundleId = BigInteger.valueOf(resultSet.getInt("orderServiceBundleId"));
                OrderServiceBundle orderServiceBundle = new OrderServiceBundle(orderServiceBundleId, orderId, serviceId);
                orderServiceBundleList.add(orderServiceBundle);
            } while(resultSet.next());

            return orderServiceBundleList;
        } else {
            throw new SQLException("OrderServiceBundles not found");
        }
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
}
