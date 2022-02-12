package com.diploma.dao;

import com.diploma.models.Order;

import java.math.BigInteger;
import java.util.Collection;

public interface OrderDao {
    Order getOrder(BigInteger orderId);

    Collection<Order> getOrders();

    Collection<Order> getOrders(String orderName);

    Boolean updateOrder(Order order);

    Boolean deleteOrder(BigInteger orderId);
}
