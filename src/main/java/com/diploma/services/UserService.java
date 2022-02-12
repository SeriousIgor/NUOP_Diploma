package com.diploma.services;

import com.diploma.dao.UserDao;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }
}
