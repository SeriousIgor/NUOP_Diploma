package com.diploma.dao.implementation;

import com.diploma.dao.WorkLogDao;
import com.diploma.models.WorkLog;
import com.diploma.models.enumeration.WorkLogAction;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
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

    public WorkLogDaoImplementation(Connection connection, Statement stm) throws SQLException {
        this.connection = connection;
        this.stm = stm;
    }


    @Override
    public WorkLog getWorkLog(BigInteger workLogId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(WorkLogDao.GET_WORKLOG + workLogId);
        WorkLog workLog = null;
        if(resultSet.next()){
            BigInteger userId = BigInteger.valueOf(resultSet.getInt("userId"));
            WorkLogAction action = WorkLogAction.valueOf(resultSet.getString("action"));
            Timestamp logDate = resultSet.getTimestamp("logDate");
            workLog = new WorkLog(workLogId, userId, action, logDate);
            return workLog;
        } else {
            throw new SQLException("WorkLog not found");
        }
    }

    @Override
    public Collection<WorkLog> getWorkLogs() throws SQLException {
        ResultSet resultSet = stm.executeQuery(WorkLogDao.GET_WORKLOGS);
        Collection<WorkLog> workLogs = new ArrayList<WorkLog>();
        if(resultSet.next()){
            do {
                BigInteger workLogId = BigInteger.valueOf(resultSet.getInt("workLogId"));
                BigInteger userId = BigInteger.valueOf(resultSet.getInt("userId"));
                WorkLogAction action = WorkLogAction.valueOf(resultSet.getString("action"));
                Timestamp logDate = resultSet.getTimestamp("logDate");
                WorkLog workLog = new WorkLog(workLogId, userId, action, logDate);
                workLogs.add(workLog);
            } while (resultSet.next());
            return workLogs;
        } else {
            throw new SQLException("WorkLogs not found");
        }
    }

    @Override
    public Collection<WorkLog> getWorkLogs(BigInteger userId) throws SQLException {
        ResultSet resultSet = stm.executeQuery(WorkLogDao.GET_WORKLOGS_BY_USER + userId);
        Collection<WorkLog> workLogs = new ArrayList<WorkLog>();
        if(resultSet.next()){
            do {
                BigInteger workLogId = BigInteger.valueOf(resultSet.getInt("workLogId"));
                WorkLogAction action = WorkLogAction.valueOf(resultSet.getString("action"));
                Timestamp logDate = resultSet.getTimestamp("logDate");
                WorkLog workLog = new WorkLog(workLogId, userId, action, logDate);
                workLogs.add(workLog);
            } while (resultSet.next());
            return workLogs;
        } else {
            throw new SQLException("WorkLogs not found");
        }
    }

    @Override
    public Collection<WorkLog> getWorkLogs(Timestamp minDate, Timestamp maxDate) throws SQLException {
        if(minDate == null){
            minDate = new Timestamp(0);
        }
        if(maxDate == null || maxDate.before(minDate)){
            maxDate = new Timestamp(new java.util.Date().getTime());
        }
        ResultSet resultSet = stm.executeQuery(WorkLogDao.GET_WORKLOGS_BY_LOGDATE + minDate + "' AND logDate <= '" + maxDate + "'");
        Collection<WorkLog> workLogs = new ArrayList<WorkLog>();
        if(resultSet.next()){
            do {
                BigInteger workLogId = BigInteger.valueOf(resultSet.getInt("workLogId"));
                BigInteger userId = BigInteger.valueOf(resultSet.getInt("userId"));
                WorkLogAction action = WorkLogAction.valueOf(resultSet.getString("action"));
                Timestamp logDate = resultSet.getTimestamp("logDate");
                WorkLog workLog = new WorkLog(workLogId, userId, action, logDate);
                workLogs.add(workLog);
            } while (resultSet.next());
            return workLogs;
        } else {
            throw new SQLException("WorkLogs not found");
        }
    }

    @Override
    public Boolean createWorkLog(WorkLog workLog) throws SQLException {
        String query = WorkLogDao.CREATE_WORKLOG + workLog.getUserId() + ", '" + workLog.getAction() + "', '" + workLog.getLogDate() + "')";
        Boolean result = stm.executeUpdate(query) == 1;
        return result;
    }
}
