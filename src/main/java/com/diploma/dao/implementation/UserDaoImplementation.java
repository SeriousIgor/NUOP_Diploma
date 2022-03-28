package com.diploma.dao.implementation;

import com.diploma.dao.UserDao;
import com.diploma.models.User;

import javax.xml.transform.Result;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDaoImplementation implements UserDao {
    private Connection connection;
    private Statement stm;

    public UserDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }

    public UserDaoImplementation(Connection connection, Statement stm) throws SQLException {
        this.connection = connection;
        this.stm = stm;
    }

    @Override
    public User getUser(BigInteger userId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USER + userId);
        return buildUser(resultSet);
    }

    @Override
    public User getUser(String userName, String password) throws SQLException {
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USER_BY_USERNAME_AND_PASSWORD + userName + "' AND password = '" + password + "'");
        return buildUser(resultSet);
    }

    @Override
    public Collection<User> getUsers() throws SQLException {
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USERS);
        return buildUserList(resultSet);
    }

    @Override
    public Collection<User> getUsers(String userName) throws SQLException {
        userName = "%" + userName + "%'";
        ResultSet resultSet = stm.executeQuery(UserDao.GET_USERS_BY_USERNAME + userName);
        return buildUserList(resultSet);
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

    private User buildUser(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            BigInteger userId = BigInteger.valueOf(resultSet.getInt("userId"));
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            Boolean isAdmin = resultSet.getInt("isAdmin") == 1;
            Boolean isDeleted = resultSet.getInt("isDeleted") == 1;
            User user = new User(userId, username, firstName, lastName, password, isAdmin, isDeleted);
            return user;
        } else {
            throw new SQLException("No records found");
        }
    }

    private Collection<User> buildUserList(ResultSet resultSet) throws SQLException {
        Collection<User> userList = new ArrayList<User>();
        if(resultSet.next()){
            do {
                userList.add(buildUser(resultSet));
            } while(resultSet.next());

            return userList;
        } else {
            throw new SQLException("No records found");
        }
    }
}
