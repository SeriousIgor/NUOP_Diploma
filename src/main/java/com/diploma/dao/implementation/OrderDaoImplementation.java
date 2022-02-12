package com.diploma.dao.implementation;

import com.diploma.dao.OrderDao;
import com.diploma.models.Order;

import java.math.BigInteger;
import java.util.Collection;

public class OrderDaoImplementation implements OrderDao {
    @Override
    public Order getOrder(BigInteger orderId) {
        return null;
    }

    @Override
    public Collection<Order> getOrders() {
        return null;
    }

    @Override
    public Collection<Order> getOrders(String orderName) {
        return null;
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
