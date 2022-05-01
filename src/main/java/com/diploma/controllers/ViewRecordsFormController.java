package com.diploma.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.diploma.helpers.FormHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewRecordsFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button cardsButton;

    @FXML
    private Button clientsButton;

    @FXML
    private Button createButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Button usersButton;

    @FXML
    private Pane viewAllRecordsPane;

    @FXML
    private Button workLogsButton;

    private FormHelper fh;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/main-menu-form.fxml");
    }

    @FXML
    void onCardsButton(ActionEvent event) {

    }

    @FXML
    void onClientsButton(ActionEvent event) {

    }

    @FXML
    void onCreateButtonClick(ActionEvent event) {

    }

    @FXML
    void onOrdersButton(ActionEvent event) {

    }

    @FXML
    void onServicesButtonClick(ActionEvent event) {

    }

    @FXML
    void onUsersButtonClick(ActionEvent event) {

    }

    @FXML
    void onWorkLogsButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        this.fh = new FormHelper();
    }

}
