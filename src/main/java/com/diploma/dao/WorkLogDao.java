package com.diploma.dao;

import com.diploma.models.WorkLog;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

public interface WorkLogDao {
    WorkLog getWorkLog(BigInteger workLogId) throws SQLException;

    Collection<WorkLog> getWorkLogs() throws SQLException;

    Collection<WorkLog> getWorkLogs(BigInteger userId) throws SQLException;

    Collection<WorkLog> getWorkLogs(Timestamp minDate, Timestamp maxDate) throws SQLException;

    Boolean createWorkLog(WorkLog workLog) throws SQLException;

    String GET_WORKLOG = "SELECT workLogId, userId, action, logDate FROM WorkLog WHERE workLogId = ";
    String GET_WORKLOGS = "SELECT workLogId, userId, action, logDate FROM WorkLog";
    String GET_WORKLOGS_BY_USER = "SELECT workLogId, userId, action, logDate FROM WorkLog WHERE userId = ";
    String GET_WORKLOGS_BY_LOGDATE = "SELECT workLogId, userId, action, logDate FROM WorkLog WHERE logDate >= '";
    String CREATE_WORKLOG = "INSERT INTO WorkLog (userId, action, logDate) VALUES (";
}
