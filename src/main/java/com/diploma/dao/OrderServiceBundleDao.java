package com.diploma.dao;

import com.diploma.models.OrderServiceBundle;
import com.diploma.models.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface OrderServiceBundleDao {
    OrderServiceBundle getOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException;

    Collection<OrderServiceBundle> getOrderServiceBundles() throws SQLException;

    Collection<OrderServiceBundle> getOrderServiceBundles(BigInteger orderId) throws SQLException;

    Collection<OrderServiceBundle> getOrderServiceBundles(BigInteger orderId, BigInteger serviceId) throws SQLException;

    Boolean createOrderServiceBundle(OrderServiceBundle orderServiceBundle) throws SQLException;

    Boolean updateOrderServiceBundle(OrderServiceBundle orderServiceBundle) throws SQLException;

    Boolean deleteOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException;

    Boolean hardDeleteOrderServiceBundle(BigInteger orderServiceBundleId) throws SQLException;

    String GET_ORDERSERVICEBUNDLE = "SELECT orderServiceBundleId, orderId, serviceId FROM OrderServiceBundle WHERE orderServiceBundleId = ";
    String GET_ORDERSERVICEBUNDLES = "SELECT orderServiceBundleId, orderId, serviceId FROM OrderServiceBundle WHERE isDeleted = 0";
    String GET_ORDERSERVICEBUNDLES_BY_ORDER = "SELECT orderServiceBundleId, orderId, serviceId FROM OrderServiceBundle WHERE orderId = ";
    String GET_ORDERSERVICEBUNDLES_BY_SERVICE_AND_ORDER = "SELECT orderServiceBundleId, orderId, serviceId FROM OrderServiceBundle WHERE orderId = ";
    String CREATE_ORDERSERVICEBUNDLE = "INSERT INTO OrderServiceBundle (orderId, serviceId) VALUES (";
    String UPDATE_ORDERSERVICEBUNDLE = "UPDATE OrderServiceBundle SET ";
    String DELETE_ORDERSERVICEBUNDLE = "UPDATE OrderServiceBundle SET isDeleted = 1 WHERE orderServiceBundleId = ";
    String HARD_DELETE_ORDERSERVICEBUNDLE = "DELETE FROM OrderServiceBundle WHERE orderServiceBundleId = ";
}
