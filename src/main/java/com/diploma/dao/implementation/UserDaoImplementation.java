package com.diploma.dao.implementation;

import com.diploma.dao.UserDao;
import com.diploma.models.User;

import java.math.BigInteger;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserDaoImplementation implements UserDao {
    private Connection connection;
    private Statement stm;

    public UserDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }

    @Override
    public User getUser(BigInteger userId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USER + userId);
        User user = null;
        if(resultSet.next()){
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            Boolean isAdmin = resultSet.getInt("isAdmin") == 1;
            user = new User(userId, username, firstName, lastName, password, isAdmin);
            return user;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Collection<User> getUsers() throws SQLException {
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USERS);
        LinkedList<User> userList = new LinkedList<>();
        if(resultSet.next()){
            do {
                BigInteger userId = BigInteger.valueOf(resultSet.getInt("userId"));
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                Boolean isAdmin = resultSet.getInt("isAdmin") == 1;
                User user = new User(userId, username, firstName, lastName, password, isAdmin);
                userList.add(user);
            } while(resultSet.next());

            return userList;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Collection<User> getUsers(String userName) throws SQLException {
        userName = "%" + userName + "%'";
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USERS_BY_USERNAME + userName);
        LinkedList<User> userList = new LinkedList<>();
        if(resultSet.next()){
            do {
                BigInteger userId = BigInteger.valueOf(resultSet.getInt("userId"));
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                Boolean isAdmin = resultSet.getInt("isAdmin") == 1;
                User user = new User(userId, username, firstName, lastName, password, isAdmin);
                userList.add(user);
            } while(resultSet.next());

            return userList;
        } else {
            throw new SQLException("No records found");
        }
    }

    @Override
    public Boolean createUser(User user) throws SQLException {
        String query = UserDao.CREATE_USER + "'" + user.getUserName() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "')";
        Boolean result = stm.executeUpdate(query) == 1;

        return result;
    }

    @Override
    public Boolean updateUser(User user) throws SQLException {
        String query = UserDao.UPDATE_USER + "username = '" + user.getUserName() + "', firstName = '" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', password = '" + user.getPassword() + "' WHERE userId = '" + user.getUserId() +"'";
        Boolean result = stm.executeUpdate(query) == 1;

        return result;
    }

    @Override
    public Boolean deleteUser(BigInteger userId) throws SQLException {
        Boolean result = stm.executeUpdate(UserDao.DELETE_USER + userId) == 1;
        return result;
    }
}
