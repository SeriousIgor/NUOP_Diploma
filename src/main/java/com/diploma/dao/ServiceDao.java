package com.diploma.dao;

import com.diploma.models.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface ServiceDao {
    Service getService(BigInteger serviceId) throws SQLException;

    Collection<Service> getServices() throws SQLException;

    Collection<Service> getServices(String name) throws SQLException;

    Collection<Service> getServices(Double minPrice, Double maxPrice) throws SQLException;

    Boolean createService(Service service) throws SQLException;

    Boolean updateService(Service service) throws SQLException;

    Boolean deleteService(BigInteger serviceId) throws SQLException;

    String GET_SERVICE = "SELECT serviceId, name, price, description FROM Service WHERE serviceId = ";
    String GET_SERVICES = "SELECT serviceId, name, price, description FROM Service WHERE isDeleted = 0";
    String GET_SERVICES_BY_NAME = "SELECT serviceId, name, price, description FROM Service WHERE name like ";
    String GET_SERVICES_BY_PRICE = "SELECT serviceId, name, price, description FROM Service WHERE price >= ";
    String CREATE_SERVICE = "INSERT INTO Service (name, price, description) VALUES (";
    String UPDATE_SERVICE = "UPDATE Service SET ";
    String DELETE_SERVICE = "UPDATE Service SET isDeleted = 1 WHERE serviceId = ";
}
