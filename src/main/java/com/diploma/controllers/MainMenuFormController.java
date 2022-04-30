package com.diploma.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.diploma.helpers.FormHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button viewRecordsButton;

    @FXML
    private Button worklogRaportsButton;

    private FormHelper fh;

    @FXML
    void onViewRecordsButtonClick(ActionEvent event) {
        Stage stage = (Stage) viewRecordsButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onWorklogRaportsButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        fh = new FormHelper();
    }

}
