package com.diploma.dao.implementation;

import com.diploma.dao.ServiceDao;
import com.diploma.models.Service;
import com.diploma.models.User;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceDaoImplementation implements ServiceDao {
    private Connection connection;
    private Statement stm;

    public ServiceDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }

    public ServiceDaoImplementation(Connection connection, Statement stm) throws SQLException {
        this.connection = connection;
        this.stm = stm;
    }

    @Override
    public Service getService(BigInteger serviceId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(ServiceDao.GET_SERVICE + serviceId);
        if(resultSet.next()){
            return buildService(resultSet);
        } else {
            throw new SQLException("Service not found");
        }
    }

    @Override
    public Collection<Service> getServices() throws SQLException {
        ResultSet resultSet = stm.executeQuery(ServiceDao.GET_SERVICES);
        return buildServiceList(resultSet);
    }

    @Override
    public Collection<Service> getServices(String serviceName) throws SQLException {
        serviceName = new StringBuilder().append("'%").append(serviceName).append("%'").toString();
        ResultSet resultSet = stm.executeQuery(ServiceDao.GET_SERVICES_BY_NAME + serviceName);
        return buildServiceList(resultSet);
    }

    @Override
    public Collection<Service> getServices(Double minPrice, Double maxPrice) throws SQLException {
        if (minPrice == null || minPrice < 0){
            minPrice = 0d;
        }
        if(maxPrice == null || maxPrice < minPrice){
            maxPrice = Double.MAX_VALUE;
        }
        ResultSet resultSet = stm.executeQuery(ServiceDao.GET_SERVICES_BY_PRICE  + minPrice + " AND price <= " + maxPrice);
        return buildServiceList(resultSet);
    }

    @Override
    public Boolean createService(Service service) throws SQLException {
        String query = ServiceDao.CREATE_SERVICE + "'" + service.getName() + "', '" + service.getPrice() + "', '" + service.getDescription() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean updateService(Service service) throws SQLException {
        String query = ServiceDao.UPDATE_SERVICE + "name = '" + service.getName() + "', price = " + service.getPrice() + ", description = '" + service.getDescription() + "' WHERE serviceId = " + service.getServiceId();
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    @Override
    public Boolean deleteService(BigInteger serviceId) throws SQLException {
        String query = ServiceDao.DELETE_SERVICE + serviceId;
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }

    private Service buildService(ResultSet resultSet) throws SQLException {
        BigInteger serviceId = BigInteger.valueOf(resultSet.getInt("serviceId"));
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        Boolean isDeleted = resultSet.getInt("isDeleted") == 1;
        Service service = new Service(serviceId, name, price, description, isDeleted);

        return service;
    }

    private Collection<Service> buildServiceList(ResultSet resultSet) throws SQLException {
        Collection<Service> serviceList = new ArrayList<Service>();
        if (resultSet.next()) {
            do {
                serviceList.add(buildService(resultSet));
            } while (resultSet.next());

            return serviceList;
        } else {
            throw new SQLException("Services not found");
        }
    }
}
