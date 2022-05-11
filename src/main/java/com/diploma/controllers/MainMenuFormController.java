package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.helpers.FormHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuFormController {

    @FXML
    private Pane mainMenuPane;

    @FXML
    private Button viewRecordsButton;

    @FXML
    private Button worklogReportsButton;

    private FormHelper fh;

    @FXML
    void onViewRecordsButtonClick(ActionEvent event) {
        Stage stage = (Stage) mainMenuPane.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onWorklogReportsButtonClick(ActionEvent event) {
        Stage stage = (Stage) mainMenuPane.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/worklog-reports-form.fxml");
    }

    @FXML
    void initialize() throws SQLException {
        fh = new FormHelper();
    }

}
