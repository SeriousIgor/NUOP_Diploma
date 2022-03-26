package com.diploma.dao;

import com.diploma.models.WorkLog;

import java.math.BigInteger;
import java.util.Collection;

public interface WorkLogDao {
    WorkLog getWorkLog(BigInteger workLogId);

    Collection<WorkLog> getWorkLogs();

    Collection<WorkLog> getWorkLogs(BigInteger userId);

    Boolean createWorkLog(WorkLog workLog);

    String GET_WORKLOG = "SELECT workLogId, userId, action, logDate FROM WorkLog WHERE workLogId = ";
    String GET_WORKLOGS = "SELECT workLogId, userId, action, logDate FROM WorkLog";
    String GET_WORKLOGS_BY_USER = "SELECT workLogId, userId, action, logDate FROM WorkLog WHERE userId = ";
    String CREATE_WORKLOG = "INSERT INTO WorkLog (userId, action, logDate) VALUES (";
}
