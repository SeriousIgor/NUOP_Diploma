package com.diploma.models;

import com.diploma.models.enumeration.WordLogAction;

import java.math.BigInteger;
import java.util.Date;

public class WorkLog {
    private BigInteger workLogId;
    private BigInteger userId;
    private WordLogAction action;
    private Date logDate;

    public WorkLog(BigInteger workLogId, BigInteger userId, WordLogAction action, Date logDate) {
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

    public WordLogAction getAction() {
        return action;
    }

    public void setAction(WordLogAction action) {
        this.action = action;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}