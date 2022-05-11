package com.diploma.controllers;

import com.diploma.dao.implementation.WorkLogDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import com.diploma.models.WorkLog;
import com.diploma.models.enumeration.WorkLogAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ViewAllWorklogsFormController {

    @FXML
    private TableColumn<WorkLog, WorkLogAction> actionColumn;

    @FXML
    private TableColumn<WorkLog, Timestamp> logDateColumn;

    @FXML
    private TableColumn<WorkLog, BigInteger> userColumn;

    @FXML
    private TableView<WorkLog> workLogTableView;

    private FormHelper fh;

    private WorkLogDaoImplementation wdi;

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.wdi = new WorkLogDaoImplementation(FormHelper.connection);
        populateWorkLogTable();
    }

    private void populateWorkLogTable() throws SQLException {
        this.actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        this.logDateColumn.setCellValueFactory(new PropertyValueFactory<>("logDate"));
        this.userColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        ObservableList<WorkLog> wrapperWorkLogRecords = FXCollections.observableArrayList();
        wrapperWorkLogRecords.addAll(wdi.getWorkLogs());
        this.workLogTableView.setItems(wrapperWorkLogRecords);
    }
}
