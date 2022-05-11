package com.diploma.dao;

import com.diploma.models.User;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;

public interface UserDao {
    User getUser(BigInteger userId) throws SQLException;

    User getUser(String userName) throws SQLException;

    User getUser(String userName, String password) throws SQLException;

    Collection<User> getUsers(String name) throws SQLException;

    Collection<User> getUsers() throws SQLException;

    Boolean createUser(User user) throws SQLException;

    Boolean updateUser(User user) throws SQLException;

    Boolean deleteUser(BigInteger userId) throws SQLException;

    String GET_USER = "SELECT userId, username, firstName, lastName, password, isAdmin, isDeleted FROM User WHERE userId = ";

    String GET_USER_BY_USERNAME = "SELECT userId, username, firstName, lastName, password, isAdmin, isDeleted FROM User WHERE username like ";

    String GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT userId, username, firstName, lastName, password, isAdmin, isDeleted FROM User WHERE username like '";

    String GET_USERS = "SELECT userId, username, firstName, lastName, password, isAdmin, isDeleted FROM User WHERE isDeleted = 0";

    String GET_USERS_BY_NAME = "SELECT userId, username, firstName, lastName, password, isAdmin, isDeleted FROM User WHERE isDeleted = 0 AND (firstName like ";

    String CREATE_USER = "INSERT INTO User (username, firstName, lastName, password, isAdmin) VALUES (";

    String UPDATE_USER = "UPDATE User SET ";

    String DELETE_USER = "UPDATE User SET isDeleted = 1 WHERE userID = ";
}
