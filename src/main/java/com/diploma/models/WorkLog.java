package com.diploma.models;

import com.diploma.models.enumeration.WorkLogAction;

import java.math.BigInteger;
import java.sql.Timestamp;

public class WorkLog {
    private BigInteger workLogId;
    private BigInteger userId;
    private WorkLogAction action;
    private Timestamp logDate;

    public WorkLog(BigInteger workLogId, BigInteger userId, WorkLogAction action, Timestamp logDate) {
        this.workLogId = workLogId;
        this.userId = userId;
        this.action = action;
        this.logDate = logDate;
    }

    public BigInteger getWorkLogId() {
        return workLogId;
    }

    public void setWorkLogId(BigInteger workLogId) {
        this.workLogId = workLogId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public WorkLogAction getAction() {
        return action;
    }

    public void setAction(WorkLogAction action) {
        this.action = action;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }
}