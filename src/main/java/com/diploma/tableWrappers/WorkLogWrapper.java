package com.diploma.tableWrappers;

import com.diploma.models.WorkLog;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WorkLogWrapper {
    private SimpleIntegerProperty workLogId;
    private SimpleIntegerProperty userId;
    private SimpleStringProperty action;
    private SimpleStringProperty logDate;

    public WorkLogWrapper(WorkLog workLog){
        this.workLogId = new SimpleIntegerProperty(workLog.getWorkLogId().intValue());
        this.userId = new SimpleIntegerProperty(workLog.getUserId().intValue());
        this.action = new SimpleStringProperty(workLog.getAction().toString());
        this.logDate = new SimpleStringProperty(workLog.getLogDate().toString());
    }
}
