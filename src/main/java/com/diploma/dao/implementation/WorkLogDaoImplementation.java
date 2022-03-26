package com.diploma.dao.implementation;

import com.diploma.dao.WorkLogDao;
import com.diploma.models.WorkLog;

import java.math.BigInteger;
import java.sql.*;
import java.util.Collection;

public class WorkLogDaoImplementation implements WorkLogDao {
    //for further development
    //new java.sql.Timestamp(new java.util.Date().getTime()) - get current date and convert to SQL
    Connection connection;
    Statement stm;

    public WorkLogDaoImplementation() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        stm = connection.createStatement();
    }


    @Override
    public WorkLog getWorkLog(BigInteger workLogId) {
        return null;
    }

    @Override
    public Collection<WorkLog> getWorkLogs() {
        return null;
    }

    @Override
    public Collection<WorkLog> getWorkLogs(BigInteger userId) {
        return null;
    }

    @Override
    public Boolean createWorkLog(WorkLog workLog) {
        return null;
    }
}
